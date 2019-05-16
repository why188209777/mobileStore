package com.phonestore.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.phonestore.entity.Image;
import com.phonestore.service.ImageService;
import com.phonestore.service.impl.ImageServiceImpl;

/**
 * Servlet implementation class ImageServlet
 */
@WebServlet("/image")
public class ImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Access-Control-Allow-Origin", "*");//跨越
		response.setHeader("Access-Control-Allow-Credentials", "true");//允许操作Cookie
		PrintWriter out = response.getWriter();
		ImageService imageService = new ImageServiceImpl();
		String op = request.getParameter("op");
		String path = request.getServletContext().getRealPath("/assets/images/phone");
		System.out.println(path);
		
		
		if ("upload".equals(op)) {
			try {
	            // 获取客户端传过来图片的二进制流             
	            InputStream stream = request.getInputStream();
	            // 以当前时间戳为图片命名             
	            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
	            String strCurrentTime = df.format(new Date());
	            //最终文件存储位置
	            String imagePath = path + "/" + strCurrentTime + ".png";
	            System.out.println("imagePath:" + imagePath);
	            // 这里的文件格式可以自行修改，如.jpg
	            FileOutputStream fos = new FileOutputStream(imagePath);
	            byte[] bbuf = new byte[32];
	            int hasRead = 0;
	            while ((hasRead = stream.read(bbuf)) > 0) {
	                fos.write(bbuf, 0, hasRead);
	                // 将文件写入服务器的硬盘上
	            }
	            fos.close();
	            stream.close();
	            /*
			             * 但是需要注意，采用这种原始的方式写入文件时，你会发现被写入的文件内容前4行并非是读取文件的真正内容，            
			             * 从第四行开始才是正文数据。第二行是文件路径以及名称。所以通常的做法是，先将文件写入临时文件中，然后
			             * 再采用RandomAccessFile读取临时文件的第四行以后部分。写入到目标文件中。              
	             */
	            Byte n;
	            // 读取临时文件           
	            RandomAccessFile random = new RandomAccessFile(imagePath, "r");
	            int second = 1;
	            String secondLine = null;
	            while (second <= 2) {
	                secondLine = random.readLine();
	                second++;
	            }
	            int position = secondLine.lastIndexOf('\\');
	            // 获取上传文件的名称
	            String fileName = secondLine.substring(position + 1, secondLine.length() - 1);
	            random.seek(0);
	            long forthEndPosition = 0;
	            int forth = 1;
	            while ((n = random.readByte()) != -1 && (forth <= 4)) {
	                if (n == '\n') {
	                    forthEndPosition = random.getFilePointer();
	                    forth++;
	                }
	            }
	            RandomAccessFile random2 = new RandomAccessFile(imagePath, "rw");
	            random.seek(random.length());
	            long endPosition = random.getFilePointer();
	            long mark = endPosition;
	            int j = 1;
	            while ((mark >= 0) && (j <= 6)) {
	                mark--;
	                random.seek(mark);
	                n = random.readByte();
	                if (n == '\n') {
	                    endPosition = random.getFilePointer();
	                    j++;
	                }
	            }
	            random.seek(forthEndPosition);
	            long startPoint = random.getFilePointer();
	            while (startPoint < endPosition - 1) {
	                n = random.readByte();
	                random2.write(n);
	                startPoint = random.getFilePointer();
	            }
	            random.close();
	            random2.close();
	            out.write(imagePath);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		}
		
		if ("renameimage".equals(op)) {
			String imageid = request.getParameter("imageid");
			String phonename = request.getParameter("phonename");
			String imagePath = request.getParameter("imagePath");
			System.out.println("imageid:" + imageid);
			System.out.println("imagePath:" + imagePath);
			File file = new File(imagePath);
			String newPath = file.getParent() + File.separator + imageid + ".png";
			System.out.println("newPath:" + newPath);
			File dest = new File(newPath);
			boolean renameTo = file.renameTo(dest);
			if (renameTo) {
				System.out.println("重命名成功");
				Image image = new Image(imageid, phonename);
				System.out.println(image);
				int addImage = imageService.addImage(image);
				if (addImage > 0) {
					out.println(true);
				}else {
					out.println(false);
				}
			}else {
				System.out.println("重命名失败");
				out.println(renameTo);
			}
			
		}
		
		
		//图片列表显示、分页以及模糊搜索
		if ("imagelist".equals(op)) {
			int pageSize = 5;
			int pageIndex = 
				request.getParameter("pageIndex") == ""
				? 1
				: Integer.parseInt(request.getParameter("pageIndex"));
			System.out.println("pageIndex:" + pageIndex);
			String key = request.getParameter("key");
			List<Image> list = null;
			System.out.println("key:" + key);
			int totalSize = 0;
			if (!"".equals(key) && key != null) {
				list = imageService.getAllImageByPage(pageIndex, pageSize, key);
				totalSize = imageService.getTotalCount(key) % pageSize == 0
						? imageService.getTotalCount(key) / pageSize
						: imageService.getTotalCount(key) / pageSize + 1;
			}else {
				list = imageService.getAllImageByPage(pageIndex, pageSize);
				
				totalSize = imageService.getTotalCount() % pageSize == 0
						? imageService.getTotalCount() / pageSize
						: imageService.getTotalCount() / pageSize + 1;
			}
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("imageList", list);
			map.put("pageIndex", pageIndex);
			map.put("totalSize", totalSize);
			String json = JSON.toJSONString(map);
			out.println(json);
		}
		
		if ("updateimage".equals(op)) {
			int id = Integer.parseInt(request.getParameter("id"));
			String imageid = request.getParameter("iamgeid");
			String phonename = request.getParameter("phonename");
			Image image = imageService.searchImage(id);
			File file = new File(path + File.separator + image.getImageid() + ".png");
			String newPath = file.getParent() + File.separator + imageid + ".png";
			System.out.println("oldPath:" + path + File.separator + image.getImageid() + ".png");
			System.out.println("newPath:" + newPath);
			File dest = new File(newPath);
			boolean renameTo = file.renameTo(dest);
			if (renameTo) {
				int updateImage = imageService.updateImage(new Image(id, imageid, phonename));
				if (updateImage > 0) {
					out.println(true);
				}else {
					out.println(false);
				}
			}else {
				out.println(false);
			}
		}
		
		if ("deleteimage".equals(op)) {
			int id = Integer.parseInt(request.getParameter("id"));
			Image image = imageService.searchImage(id);
			int delUser = imageService.delImage(id);
			File file = new File(path + "/" + image.getImageid() + ".png");
			boolean delete = file.delete();
			System.out.println("delUser:" + delUser);
			System.out.println("delete:" + delete);
			if (delUser > 0 && delete == true) {
				out.println(true);
			}else {
				out.println(false);
			}
		}
		
		if ("imageinfo".equals(op)) {
			int id = Integer.parseInt(request.getParameter("id"));
			Image image = imageService.searchImage(id);
			String json = JSON.toJSONString(image);
			out.println(json);
		}
    }

}
