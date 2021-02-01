package download.base;

import java.util.ArrayList;
import java.util.List;

public interface UnitUrl {

    List<String> imageUrlList = new ArrayList<>();

    default void initImageUrl() {
        imageUrlList.add("https://t7.baidu.com/it/u=1956604245,3662848045&fm=193&f=GIF");
        imageUrlList.add("https://t7.baidu.com/it/u=2529476510,3041785782&fm=193&f=GIF");
        imageUrlList.add("https://t7.baidu.com/it/u=727460147,2222092211&fm=193&f=GIF");
        imageUrlList.add("https://t7.baidu.com/it/u=2511982910,2454873241&fm=193&f=GIF");
        imageUrlList.add("https://t7.baidu.com/it/u=825057118,3516313570&fm=193&f=GIF");
    }

}
