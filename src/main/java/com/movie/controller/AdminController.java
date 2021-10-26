package com.movie.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.movie.dto.AdminVO;
import com.movie.dto.MemberVO;
import com.movie.dto.MovieVO;
import com.movie.dto.Paging;
import com.movie.dto.QnaVO;
import com.movie.service.AdminService;
import com.movie.service.MovieService;
import com.movie.service.OrderService;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@Controller
public class AdminController {

   @Autowired
   AdminService as;
   
   @Autowired
   MovieService mvs;
   
   @Autowired
   OrderService os;
   
   @Autowired
   ServletContext context;
   
   @RequestMapping("loginManagerForm")
   public String loginManagerForm(Model model, HttpServletRequest request) {
      return "admin/loginManager";
   }
   
   @RequestMapping("loginManager")
   public String loginManager(Model model, HttpServletRequest request, @RequestParam("id") String id,
         @RequestParam("pwd") String pwd) {
      
      AdminVO mvo = as.getManager(id); 
      
      
       if( mvo == null ) {
          request.setAttribute("message", "아이디가 없어요");
       } else if( mvo.getPwd() == null ) {
          request.setAttribute("message", "회원정보 오류. 관리자에게 문의하세요");
       } else if( !mvo.getPwd().equals(pwd) ) {
          request.setAttribute("message", "비밀번호가 틀려요");
       } else {
          HttpSession session=request.getSession();
          session.setAttribute("adminUser", mvo);
          return "redirect:/"; 
       }

      return "admin/loginManager";
   }
   @RequestMapping("adminLogout")
   public String logout(Model model, HttpServletRequest request) {
      HttpSession session = request.getSession();
      session.removeAttribute("adminUser");
      return "redirect:/";
   }
   @RequestMapping("adminEdit")
   public String editManager(Model model, HttpServletRequest request) {
      return "admin/UpdateManager";
   }
   
   @RequestMapping(value="adminUpdate", method=RequestMethod.POST)
   public String adminUpdate(@ModelAttribute("admin") @Valid AdminVO adminvo,
   BindingResult result, Model model, HttpServletRequest request){
      
      if( result.getFieldError("pwd")!=null) {
         request.setAttribute("message", result.getFieldError("pwd").getDefaultMessage());
         return "redirect:/UpdataManager";
      }else if( result.getFieldError("name")!=null) {
         request.setAttribute("message", result.getFieldError("name").getDefaultMessage());
         return "redirect:/UpdataManager";
      }else if( result.getFieldError("email")!=null) {
         request.setAttribute("message", result.getFieldError("email").getDefaultMessage());
         return "redirect:/UpdataManager";
      }else if( !request.getParameter("pwdCheck").equals( adminvo.getPwd() ) ) {
         request.setAttribute("message", "비밀번호 확인 일치하지 않습니다");
         return "redirect:/UpdataManager";
      }
      as.updateManager(adminvo);
      HttpSession session = request.getSession();
      session.setAttribute("adminUser", adminvo);
      return "redirect:/";
   }
   
   @RequestMapping(value="adminMovieList")
   public ModelAndView adminMovieList(Model model, HttpServletRequest request) {
      
      ModelAndView mav = new ModelAndView();
      HttpSession session = request.getSession();
      AdminVO id = (AdminVO)session.getAttribute("adminUser");
      
      int page=1; 
      
      if( id == null) { 
         mav.setViewName("redirect:/loginManager");
      }else {
         if( request.getParameter("first") != null ) {
            session.removeAttribute("page");
            session.removeAttribute("key");
         }         
      
         String key = "";
         if( request.getParameter("key") != null ) {
            key = request.getParameter("key");
            session.setAttribute("key", key);
         }else {
            session.removeAttribute("key");
            key = "";
            
         }
         
         if( request.getParameter("page") != null ) {
            page = Integer.parseInt(request.getParameter("page"));
            session.setAttribute("page", page);
         }else {
            page = 1;
            session.removeAttribute("page");
         }

      
      Paging paging = new Paging();
      paging.setPage(page);
      
      int count = as.getAllCount("movie","title",key);
      paging.setTotalCount(count);
      paging.paging();
      
      List<MovieVO> movieList = as.listMovie(paging, key);
      
      mav.addObject("Movielist", movieList);
      mav.addObject("paging", paging);
      mav.addObject("key", key);
      mav.setViewName("admin/movie/movieList");

      }
   
      return mav;
   }
   @RequestMapping("AdminMovieWriteForm")
   public ModelAndView AdminMovieWriteForm( HttpServletRequest request) {
      HttpSession session = request.getSession();
      ModelAndView mav = new ModelAndView();
      AdminVO avo = (AdminVO)session.getAttribute("adminUser");
      if( avo == null) { 
         mav.setViewName( "admin/loginManager");
      }else {
         String genreList[] = {"","액션", "스릴러", "멜로", "코미디", 
               "공포", "애니메이션", "판타지", "드라마"}; 
         mav.addObject("genreList", genreList);
         mav.setViewName("admin/movie/movieWrite");
      }
      return mav;
   }
   
   @RequestMapping(value="adminMovieWrite" , method = RequestMethod.POST)
   public String adminMovieWrite( Model model , HttpServletRequest request) {
      String savePath = context.getRealPath("/files");
      MultipartRequest multi=null;
      MovieVO mvo = new MovieVO();
      try {
         multi = new MultipartRequest(request, savePath, 100*1024*1024,
               "UTF-8", new DefaultFileRenamePolicy()  );
         mvo.setTitle(multi.getParameter("title"));
         mvo.setActor(multi.getParameter("actor"));
         mvo.setGenre(multi.getParameter("genre"));
         mvo.setDirector(multi.getParameter("director"));
         mvo.setAgegrade(multi.getParameter("agegrade"));
         mvo.setPlaytime(multi.getParameter("playtime"));
         mvo.setOpendate(multi.getParameter("opendate"));
         mvo.setContent(multi.getParameter("content"));
          mvo.setImage(multi.getFilesystemName("image"));
          mvo.setVideo(multi.getFilesystemName("video"));
         as.insertMovie(mvo);
      } catch (IOException e) { e.printStackTrace();   }
      return "redirect:/adminMovieList";
   }
   
   
   
   @RequestMapping(value="adminQnaList")
      public ModelAndView adminQnaList(Model model, HttpServletRequest request) {
      ModelAndView mav = new ModelAndView();
      HttpSession session = request.getSession();
         AdminVO id = (AdminVO)session.getAttribute("adminUser");
         
         
         if( id == null) { 
            mav.setViewName("redirect:/loginManager");  
         }else {
            if( request.getParameter("first") != null ) {
               session.removeAttribute("page");
               session.removeAttribute("key");
            }
            String key="";
            if( request.getParameter("key") != null ) {
               key = request.getParameter("key");
               session.setAttribute("key", key);
            }else {
               session.removeAttribute("key");
               key = "";
               
            }
            int page=1; 
            
            if( request.getParameter("page") != null ) {
               page = Integer.parseInt(request.getParameter("page"));
               session.setAttribute("page", page);
            } else {
               page = 1;
               session.removeAttribute("page");
            }
            
            Paging paging = new Paging();
            paging.setPage(page);
            
            int count = as.getAllCount("qna", "subject" , key);
            paging.setTotalCount(count);
            paging.paging();
            
            List<QnaVO> qnaList = as.listQna(paging, key);
            
            mav.addObject("qnaList", qnaList);
            mav.addObject("paging", paging);
            mav.addObject("key", key);
            mav.setViewName("admin/qna/qnaList");
            }
         return mav;
      
   }

   @RequestMapping("adminMovieDetail")
   public ModelAndView adminMovieDetail(@RequestParam("movieno") int movieno, 
         HttpServletRequest request) {
      ModelAndView mav = new ModelAndView();
      mav.addObject("MovieVO", mvs.getMovie(movieno));
      String genreList[] = {"", "액션", "스릴러", "멜로", "코미디", 
            "공포", "애니메이션", "판타지", "드라마" };    
      int index = Integer.parseInt(mvs.getMovie(movieno).getGenre());
      
      mav.addObject("genreList", genreList[index]);
      mav.setViewName("admin/movie/movieDetail");
      return mav;
   }
   
   
   @RequestMapping("adminMovieUpdateForm")
   public ModelAndView adminMovieUpdateForm(@RequestParam("movieno") int movieno, 
      HttpServletRequest request) {
      ModelAndView mav = new ModelAndView();
      MovieVO mvo = mvs.getMovie(movieno);
      String genreList[] = {"","액션", "스릴러", "멜로", "코미디", 
            "공포", "애니메이션", "판타지", "드라마" };    
      int index = Integer.parseInt(mvo.getGenre());
      request.setAttribute("genre", genreList[index]);
      
      mav.addObject("genreList", genreList);
      mav.addObject("MovieVO", mvo);
      mav.setViewName("admin/movie/movieUpdate");
      
      System.out.println(mvo);
      
      return mav;
   }
   
   @RequestMapping(value="adminMovieUpdate", method = RequestMethod.POST)
   public String adminMovieUpdate(   HttpServletRequest request) throws IOException {

         MovieVO mvo = new MovieVO();
         MultipartRequest multi=null;
          String path = context.getRealPath("/files");
          try {
          multi = new MultipartRequest( request, 
          path,   28*2048*2048, "UTF-8",   new DefaultFileRenamePolicy());
         
          mvo.setMovieno(Integer.parseInt( multi.getParameter("movieno")));
          mvo.setTitle( multi.getParameter("title"));
          mvo.setDirector( multi.getParameter("director"));
          mvo.setActor( multi.getParameter("actor"));
          mvo.setGenre( multi.getParameter("genre"));
          mvo.setAgegrade( multi.getParameter("agegrade"));
          mvo.setPlaytime(multi.getParameter("playtime"));
          mvo.setOpendate(multi.getParameter("opendate"));
          mvo.setUseyn(multi.getParameter("useyn"));
          mvo.setContent(multi.getParameter("content"));
          
          if(mvo.getUseyn()==null) {
             mvo.setUseyn("n");
          }
          if(multi.getFilesystemName("image") == null )
             mvo.setImage(multi.getParameter("oldImage") );
          else
             mvo.setImage( multi.getFilesystemName("image") );
         
          if(multi.getFilesystemName("video") == null )
           mvo.setVideo(multi.getParameter("oldVideo") ); 
         else 
            mvo.setVideo( multi.getFilesystemName("video") );
          }catch(IOException e) {e.printStackTrace();}
          as.updateMovie(mvo);
      return "redirect:/adminMovieDetail?movieno="+mvo.getMovieno();
   }

         
      
   
   
   @RequestMapping("adminQnaDetail")
   public ModelAndView admin_qna_detail(@RequestParam("qseq") int qseq, 
         HttpServletRequest request) {
      ModelAndView mav = new ModelAndView();
      QnaVO qvo = as.getQna(qseq);
      mav.addObject("qnaVO", qvo);
      mav.setViewName("admin/qna/qnaDetail");
      return mav;
   }
   
   @RequestMapping("adminQnaRepsave")
   public String admin_qna_repsave(Model model, HttpServletRequest request) {
      String qseq = request.getParameter("qseq");
      String reply =request.getParameter("reply");
      QnaVO qvo = new QnaVO();
      qvo.setQseq(Integer.parseInt(qseq));
      qvo.setReply(reply);
      as.updateQna(qvo);
      return "redirect:/adminQnaDetail?qseq=" + qseq;
   }

   
   @RequestMapping(value="adminMemberList")
   public ModelAndView adminMemberList(Model model, HttpServletRequest request) {
      
      ModelAndView mav = new ModelAndView();
      HttpSession session = request.getSession();
      AdminVO id = (AdminVO)session.getAttribute("adminUser");
      
      int page=1; 
      
      if( id == null) { 
         mav.setViewName("redirect:/loginManager");
      }else {
         if( request.getParameter("first") != null ) {
            session.removeAttribute("page");
            session.removeAttribute("key");
         }
         String key="";
         if( request.getParameter("key") != null ) {
            key = request.getParameter("key");
            session.setAttribute("key", key);
         } else {
            session.removeAttribute("key");
            key = "";
         }
         
         if( request.getParameter("page") != null ) {
            page = Integer.parseInt(request.getParameter("page"));
            session.setAttribute("page", page);
         } else {
            page = 1;
            session.removeAttribute("page");
         }
         
         Paging paging = new Paging();
         paging.setPage(page);
         
         int count1 = as.getAllCount("members","id",key);
         paging.setTotalCount(count1);
         paging.paging();
         
         List<MemberVO> memberList = as.listMember(paging, key);
         
         mav.addObject("memberList", memberList);
         mav.addObject("paging", paging);
         mav.addObject("key", key);
         mav.setViewName("admin/memberList");

         }
      
      return mav;
   }
   
   @RequestMapping(value="adminMovieDelete")
   public String adminMovieDelete(Model model, HttpServletRequest request) {
      
      HttpSession session = request.getSession();
      AdminVO avo = (AdminVO) session.getAttribute("adminUser");
      
      if(avo==null) {
         return "redirect:/loginManager";
      }else{
         int movieno = Integer.parseInt(request.getParameter("movieno"));
         
         System.out.println("movieno->"+movieno);
         
         mvs.deleteMovie(movieno);
         os.deleteMovie(movieno);
         as.movieDelete(movieno);
      }
      return "redirect:/adminMovieList";
   }
   
   
   
}