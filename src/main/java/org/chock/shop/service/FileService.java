package org.chock.shop.service;


/**
 * @auther: zhuohuahe
 * @date: 2019/11/6 18:40
 * @description:
 */
public class FileService {

//    public File convertToFile(MultipartFile multipartFile){
//        File file = new File(uploadImagePath + multipartFile.getOriginalFilename());
//        try (InputStream in  = multipartFile.getInputStream(); OutputStream os = new FileOutputStream(file)){
//            // 得到文件流。以文件流的方式输出到新文件
//            // 可以使用byte[] ss = multipartFile.getBytes();代替while
//            byte[] buffer = new byte[4096];
//            int n;
//            while ((n = in.read(buffer,0,4096)) != -1){
//                os.write(buffer,0,n);
//            }
//            os.close();
//            in.close();
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//        return file;
//    }
}
