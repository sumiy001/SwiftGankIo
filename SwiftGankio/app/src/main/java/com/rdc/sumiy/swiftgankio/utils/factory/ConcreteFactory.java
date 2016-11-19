package com.rdc.sumiy.swiftgankio.utils.factory;
/**
 * Created by sumiy on 2016/8/16.
 */
public class ConcreteFactory extends Factory {
    private ConcreteFactory(){}
    public  static  ConcreteFactory getInstances(){
        return ConcreteFactoryHolder.concreteFactory;
    }
    static  class  ConcreteFactoryHolder{
        private static ConcreteFactory concreteFactory = new ConcreteFactory();
    }
    @Override
    public <T extends ArticleTitle> T createArticleTitle(Class<T> clz) {
        ArticleTitle articleTitle = null;
        try {
            articleTitle = (ArticleTitle) Class.forName(clz.getName()).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (T) articleTitle;
    }
}
