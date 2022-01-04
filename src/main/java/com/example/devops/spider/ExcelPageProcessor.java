package com.example.devops.spider;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

/**
 * @description:
 * @author: sg zhu
 * @create: 2022-01-04 16:20
 */
public class ExcelPageProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    @Override
    public void process(Page page) {
        //抓取到的页面为一个page对象
        //我们从page里面获取Html信息
        Html html = page.getHtml();

        //获取item标签
        Selectable item =  html.$("item");

        //获取items标签的 title 中的文字
        Selectable title =  item.$("title","text");
        //获取items标签的 guid 中的文字
        Selectable link =  item.$("guid","text");

        title = title.replace("\\<!\\[CDATA\\[","");
        title = title.replace("\\]>","");

        page.putField("text",title.all());
        page.putField("href",link.all());
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new ExcelPageProcessor())
                .addUrl("https://blog.csdn.net/qq_18604209/rss/list")
                .addPipeline(new ExcelPipeline("D:\\webmagic\\"))
                .thread(5)
                .run();
    }
}
