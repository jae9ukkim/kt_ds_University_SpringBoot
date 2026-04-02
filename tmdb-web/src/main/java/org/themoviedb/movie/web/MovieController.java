package org.themoviedb.movie.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.themoviedb.movie.service.MovieService;
import org.themoviedb.movie.vo.MovieVO;
import org.themoviedb.movie.vo.request.UpdateVO;
import org.themoviedb.movie.vo.request.WriteVO;
import org.themoviedb.movie.vo.response.SearchResultVO;

import jakarta.validation.Valid;

@Controller
public class MovieController {
    
    @Autowired
    private MovieService movieService;
    
    // 영화 목록 조회 엔드포인트 생성 ( /list ) - 데이터베이스에서 조회
    @GetMapping("/list")
    public String viewMovieListPage(Model model) {
        SearchResultVO searchResult = this.movieService.findMovieAll();
        
        model.addAttribute("count", searchResult.getCount());
        model.addAttribute("movieList", searchResult.getMovieLst());
        
        return "movie/movieList";
    }

// 영화 등록 엔드포인트 생성 ( /write ) - 영화 등록 페이지 보여주기
    @GetMapping("/write")
    public String viewWritePage() {
        
        return "movie/write";
    }
    
// 영화 등록 처리 엔드포인트 생성 ( /write ) - 영화 등록 처리 (insert 수행)
    @PostMapping("/write")
    public String doWriteAction(@Valid @ModelAttribute WriteVO writeVO, BindingResult bindingResult, Model model) {

      if(bindingResult.hasErrors()) {
          model.addAttribute("inputData",writeVO);
          return "movie/write";
      }
        
      boolean createResult = this.movieService.createNewMovie(writeVO);
        
      return "redirect:/list";
    }
    
    @GetMapping("/view/{movieId}")
    public String viewMovieDetailPage(@PathVariable String movieId, Model model) {
    	
    	MovieVO movieVO = this.movieService.findMovieDtailByMovieId(movieId);
    	model.addAttribute("movie", movieVO);
    	
    	return "movie/view";
    }
    
    @GetMapping("/update/{movieId}")
    public String viewUpdatePage(@PathVariable String movieId, Model model) {
        MovieVO movieVO = this.movieService.findMovieDtailByMovieId(movieId);
        model.addAttribute("movie", movieVO);
        
    	return "movie/update";
    }
    
    @PostMapping("/update/{movieId}")
    public String doUpdateAction(@PathVariable String movieId, UpdateVO updateVO) {
    	
    	updateVO.setMovieId(movieId);
    	boolean updateResult = this.movieService.updateMovieByMovieId(updateVO);
    	System.out.println("update 성공: " + updateResult);
    	
    	return "redirect:/view/"+movieId;
    }
    
    @GetMapping("/delete")
    public String doDeleteAction(@RequestParam String movieId) {
    	
    	Boolean deleteResult = this.movieService.deleteMovieByMovieId(movieId);
    	System.out.println("delete 성공: " + deleteResult);
    	
    	return "redirect:/list";
    }
}
