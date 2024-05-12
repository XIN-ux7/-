package com.liddhome.admin.web;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import javax.imageio.ImageIO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.liddhome.entity.Book;
import com.liddhome.entity.Category;
import com.liddhome.pager.PageBean;
import com.liddhome.service.BookService;
import com.liddhome.service.CategoryService;
import com.liddhome.util.commons.CommonUtil;

@Controller
@RequestMapping("/admin/book")
public class AdminBookController{

	@Autowired
	private CategoryService categoryService;
	@Autowired
	private BookService bookService;
	
	Properties prop = new Properties();
	InputStream in = this.getClass().getClassLoader().
			getResourceAsStream("config.properties");


	@RequestMapping(value="/addBook.do", method=RequestMethod.POST)
	public String addBook(ModelMap map, @RequestParam MultipartFile[] fileItem, Book book, Category category) throws IOException {
		// 设置图书所属的分类
		book.setCategory(category);

		// 检查是否上传了大图
		if (fileItem[0].isEmpty()) {
			map.addAttribute("msg", "您还未上传图书大图！");
			map.addAttribute("parents", categoryService.findParents());
			return "/adminjsps/admin/book/add";
		}

		// 处理大图文件
		MultipartFile largeImage = fileItem[0];
		BufferedImage largeImageBuffer = ImageIO.read(largeImage.getInputStream());
		BufferedImage resizedLargeImage = resizeImage(largeImageBuffer, 350, 350);

		// 设置大图路径给Book对象
		String largeImageFilename = CommonUtil.uuid() + "_" + largeImage.getOriginalFilename();
		String largeImagePath = saveImage(resizedLargeImage, largeImageFilename);
		book.setImage_w("book_img/" + largeImagePath);

		// 检查是否上传了小图
		if (fileItem[1].isEmpty()) {
			map.addAttribute("msg", "您还未上传图书小图！");
			map.addAttribute("parents", categoryService.findParents());
			return "/adminjsps/admin/book/add";
		}

		// 处理小图文件
		MultipartFile smallImage = fileItem[1];
		BufferedImage smallImageBuffer = ImageIO.read(smallImage.getInputStream());
		BufferedImage resizedSmallImage = resizeImage(smallImageBuffer, 200, 200);

		// 设置小图路径给Book对象
		String smallImageFilename = CommonUtil.uuid() + "_" + smallImage.getOriginalFilename();
		String smallImagePath = saveImage(resizedSmallImage, smallImageFilename);
		book.setImage_b("book_img/" + smallImagePath);

		// 添加图书到数据库
		book.setBid(CommonUtil.uuid());
		bookService.addBook(book);
		map.addAttribute("msg", "添加图书成功！");

		return "/adminjsps/msg";
	}


	@RequestMapping("/delete.do")
	public String delete(ModelMap map,String bid)throws IOException {
		Book book = bookService.load(bid);
		prop.load(in);
		String savepath = prop.getProperty("savePath");
		new File(savepath,book.getImage_w().substring(book.getImage_w().lastIndexOf("/"))).delete();
		new File(savepath,book.getImage_b().substring(book.getImage_w().lastIndexOf("/"))).delete();
		bookService.delete(bid);
		map.addAttribute("msg", "删除成功！");
		return "/adminjsps/msg";
	}
	
	@RequestMapping("/edit.do")
	public String edit(ModelMap map,Book book,Category category){
		book.setCategory(category);
		bookService.edit(book);
		map.addAttribute("msg", "修改图书成功！");
		return "/adminjsps/msg";
	}
	
	@RequestMapping("/findCategoryAll.do")
	public String findCategoryAll(ModelMap map){
		List<Category> parents = categoryService.findAll();
		map.addAttribute("parents", parents);
		return "/adminjsps/admin/book/left";
	}
	
	@RequestMapping("/load.do")
	public String load(ModelMap map,String bid){
		Book book = bookService.load(bid);
		map.addAttribute("book", book);
		map.addAttribute("parents", categoryService.findParents());
		String pid = book.getCategory().getParent().getCid();
		map.addAttribute("children", categoryService.findByParent(pid));
		return "/adminjsps/admin/book/desc";
	}
	
	private int getPc(HttpServletRequest request){
		int pc = 1;
		String param = request.getParameter("pc");
		if(param!=null && !param.trim().isEmpty()){
			try{
				pc = Integer.parseInt(param);
			}catch(RuntimeException e){ }
		}
		return pc;
	}
	
	private String getUrl(HttpServletRequest request){
		String url = request.getRequestURI()+"?"+request.getQueryString();
		int index = url.lastIndexOf("&pc=");
		if(index!=-1){
			url = url.substring(0, index);
		}
		return url;
	}
	
	@RequestMapping("/findByCategory.do")
	public String findByCategory(HttpServletRequest request){
		int pc = getPc(request);
		String url = getUrl(request);
		String cid = request.getParameter("cid");
		PageBean<Book> pb = bookService.findByCategory(cid, pc);
		pb.setUrl(url);
		request.setAttribute("pb", pb);
		return "/adminjsps/admin/book/list";
	}
	
	@RequestMapping("/findByAuthor.do")
	public String findByAuthor(HttpServletRequest request){
		int pc = getPc(request);
		String url = getUrl(request);
		String author = request.getParameter("author");
		PageBean<Book> pb = bookService.findByAuthor(author, pc);
		pb.setUrl(url);
		request.setAttribute("pb", pb);
		return "/adminjsps/admin/book/list";
	}
	
	@RequestMapping("/findByPress.do")
	public String findByPress(HttpServletRequest request){
		int pc = getPc(request);
		String url = getUrl(request);
		String press = request.getParameter("press");
		PageBean<Book> pb = bookService.findByPress(press, pc);
		pb.setUrl(url);
		request.setAttribute("pb", pb);
		return "/adminjsps/admin/book/list";
	}
	
	@RequestMapping("/findByBname.do")
	public String findByBname(HttpServletRequest request){
		int pc = getPc(request);
		String url = getUrl(request);
		String bname = request.getParameter("bname");
		PageBean<Book> pb = bookService.findByBname(bname, pc);
		pb.setUrl(url);
		request.setAttribute("pb", pb);
		return "/adminjsps/admin/book/list";
	}
	
	@RequestMapping("/findByConbination.do")
	public String findByConbination(HttpServletRequest request,Book book){
		int pc = getPc(request);
		String url = getUrl(request);
		PageBean<Book> pb = bookService.findByConbination(book, pc);
		pb.setUrl(url);
		request.setAttribute("pb", pb);
		return "/adminjsps/admin/book/list";
	}
	
	@RequestMapping("/addPre.do")
	public String addPre(ModelMap map){
		List<Category> parents = categoryService.findParents();
		map.addAttribute("parents", parents);
		return "/adminjsps/admin/book/add";
	}
	
	@RequestMapping("/ajaxFindChildren.do")
	@ResponseBody
	public List<Category> ajaxFindChildren(String pid){
		List<Category> children = categoryService.findByParent(pid);
		return children;
	}

	private BufferedImage resizeImage(BufferedImage image, int width, int height) {
		Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics = resizedImage.createGraphics();
		graphics.drawImage(scaledImage, 0, 0, null);
		graphics.dispose();
		return resizedImage;
	}

	private String saveImage(BufferedImage image, String filename) throws IOException {
		prop.load(in);
		String savePath = prop.getProperty("savePath");
		String imagePath = savePath + filename;
		File destFile = new File(imagePath);
		ImageIO.write(image, "jpg", destFile);
		return filename;
	}
}
