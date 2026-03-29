package org.themoviedb.movie.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.themoviedb.movie.service.MovieService;
import org.themoviedb.movie.vo.request.WriteVO;
import org.themoviedb.movie.vo.response.SearchResultVO;

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
    public String doWriteAction(WriteVO writeVO) {
        System.out.println(writeVO);
        
        
//      boolean createResult = this.movieService.createNewMovie(writeVO);
        
        return "redirect:/list";
    }
}
