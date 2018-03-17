package mjx.children.joy.data;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import mjx.children.joy.bean.DataBean;
import mjx.children.joy.utils.LogUtil;
import mjx.children.joy.utils.ui.UIUtil;

/**
 * 获取数据
 * Created by MJX on 2018/3/6.
 */
public class XmlData {
    private static ArrayList assetsList = new ArrayList();
    /**
     * 通过文件名获取数据
     * @return
     */
    public static List<DataBean> getData(String fileName)
    {
        try {
            if(assetsList == null){
                assetsList = new ArrayList();
            }
            assetsList.clear();
            //传入文件名：language.xml；用来获取流
            InputStream is = UIUtil.getContext().getAssets().open(fileName);
            //首先创造：DocumentBuilderFactory对象
            DocumentBuilderFactory dBuilderFactory = DocumentBuilderFactory.newInstance();
            //获取：DocumentBuilder对象
            DocumentBuilder dBuilder = dBuilderFactory.newDocumentBuilder();
            //将数据源转换成：document 对象
            Document document = dBuilder.parse(is);
            //获取根元素
            Element element = (Element) document.getDocumentElement();
            //获取子对象的数值 读取lan标签的内容
            NodeList nodeList = element.getElementsByTagName("story");
            for (int i = 0; i < nodeList.getLength(); i++) {
                //获取对应的对象
                 Element lan = (Element) nodeList.item(i);
                 String name = lan.getElementsByTagName("name").item(0).getTextContent();
                 String content = lan.getElementsByTagName("content").item(0).getTextContent();
                 String img = lan.getElementsByTagName("img").item(0).getTextContent();
                 String link = lan.getElementsByTagName("link").item(0).getTextContent();
                 DataBean dataBean = new DataBean(name,content,img,link);
                 assetsList.add(dataBean);
                 LogUtil.logMsg("name是："+name+"----content是："+content+"------img是："+img);
            }
        }catch (Exception e){
            LogUtil.logMsg("解析数据异常"+e.toString());
        }
        return assetsList;
    }

    public static String getAssetData(String fileName) {
        String result = "";
        try{
            InputStream is = UIUtil.getContext().getAssets().open(fileName);
            int lenght = is.available();
            byte[]  buffer = new byte[lenght];
            is.read(buffer);
            result  = new String(buffer, "utf8");
        }catch (Exception e){
            LogUtil.logMsg("获取assets文件夹下的数据异常"+e.toString());
            return result;
        }
        return result;
    }

}
