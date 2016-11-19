package com.rdc.sumiy.swiftgankio.utils.factory;

/**
 * Created by sumiy on 2016/8/16.
 */
abstract  public class Factory {
    public  abstract <T extends  ArticleTitle> T createArticleTitle(Class<T> clz);
}
