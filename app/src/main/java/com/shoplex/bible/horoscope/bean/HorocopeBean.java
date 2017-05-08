package com.shoplex.bible.horoscope.bean;

/**
 * Created by qsk on 2017/4/25.
 */

public class HorocopeBean {


    /**
     * message : Successfully get book<6>
     * code : 200
     * data : {"score":9,"update_timestamp":1489039719,"author":"gulong","introduction":"<p>Acclaimed by many to be one of the finest, most emotionally moving works by acclaimed Wuxia novelist Gulong. This translation was originally made by translators: metwin1 (00-03), Chowbeng (04-07), and RWX (8-24).<\/p> <p>Fu Hongxue was a cripple, born with a lame leg and subject to epileptic seizures. He was also one of the most powerful, legendary figures of the martial arts world, with a dull black saber that was second to none. His fame made him a frequent target of challengers, but whenever his saber left its sheath, only corpses would remain in its wake. One day, however, Fu Hongxue rescued someone whom he should have killed&#8230;and in doing so, set of a chain of events and a conspiracy that would rock the world.<\/p>","cover":"https://dajvcwee6u41f.cloudfront.net/books/6.png","views":2172,"title_en":"Horizon, Bright Moon, Sabre","links":{"self":"https://api.beta.wuxiamagic.com/books/6"},"word_count":122164,"translator":"","chapter_count":25,"title_zh":"天涯明月刀","is_collected":false,"create_timestamp":0,"keywords":"","collection_id":0,"id":6,"is_published":true}
     */

    private String message;
    private int code;
    private DataBean data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * score : 9
         * update_timestamp : 1489039719
         * author : gulong
         * introduction : <p>Acclaimed by many to be one of the finest, most emotionally moving works by acclaimed Wuxia novelist Gulong. This translation was originally made by translators: metwin1 (00-03), Chowbeng (04-07), and RWX (8-24).</p> <p>Fu Hongxue was a cripple, born with a lame leg and subject to epileptic seizures. He was also one of the most powerful, legendary figures of the martial arts world, with a dull black saber that was second to none. His fame made him a frequent target of challengers, but whenever his saber left its sheath, only corpses would remain in its wake. One day, however, Fu Hongxue rescued someone whom he should have killed&#8230;and in doing so, set of a chain of events and a conspiracy that would rock the world.</p>
         * cover : https://dajvcwee6u41f.cloudfront.net/books/6.png
         * views : 2172
         * title_en : Horizon, Bright Moon, Sabre
         * links : {"self":"https://api.beta.wuxiamagic.com/books/6"}
         * word_count : 122164
         * translator :
         * chapter_count : 25
         * title_zh : 天涯明月刀
         * is_collected : false
         * create_timestamp : 0
         * keywords :
         * collection_id : 0
         * id : 6
         * is_published : true
         */

        private int score;
        private int update_timestamp;
        private String author;
        private String introduction;
        private String cover;
        private int views;
        private String title_en;
        private LinksBean links;
        private int word_count;
        private String translator;
        private int chapter_count;
        private String title_zh;
        private boolean is_collected;
        private int create_timestamp;
        private String keywords;
        private int collection_id;
        private int id;
        private boolean is_published;

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public int getUpdate_timestamp() {
            return update_timestamp;
        }

        public void setUpdate_timestamp(int update_timestamp) {
            this.update_timestamp = update_timestamp;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getIntroduction() {
            return introduction;
        }

        public void setIntroduction(String introduction) {
            this.introduction = introduction;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public int getViews() {
            return views;
        }

        public void setViews(int views) {
            this.views = views;
        }

        public String getTitle_en() {
            return title_en;
        }

        public void setTitle_en(String title_en) {
            this.title_en = title_en;
        }

        public LinksBean getLinks() {
            return links;
        }

        public void setLinks(LinksBean links) {
            this.links = links;
        }

        public int getWord_count() {
            return word_count;
        }

        public void setWord_count(int word_count) {
            this.word_count = word_count;
        }

        public String getTranslator() {
            return translator;
        }

        public void setTranslator(String translator) {
            this.translator = translator;
        }

        public int getChapter_count() {
            return chapter_count;
        }

        public void setChapter_count(int chapter_count) {
            this.chapter_count = chapter_count;
        }

        public String getTitle_zh() {
            return title_zh;
        }

        public void setTitle_zh(String title_zh) {
            this.title_zh = title_zh;
        }

        public boolean isIs_collected() {
            return is_collected;
        }

        public void setIs_collected(boolean is_collected) {
            this.is_collected = is_collected;
        }

        public int getCreate_timestamp() {
            return create_timestamp;
        }

        public void setCreate_timestamp(int create_timestamp) {
            this.create_timestamp = create_timestamp;
        }

        public String getKeywords() {
            return keywords;
        }

        public void setKeywords(String keywords) {
            this.keywords = keywords;
        }

        public int getCollection_id() {
            return collection_id;
        }

        public void setCollection_id(int collection_id) {
            this.collection_id = collection_id;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public boolean isIs_published() {
            return is_published;
        }

        public void setIs_published(boolean is_published) {
            this.is_published = is_published;
        }

        public static class LinksBean {
            /**
             * self : https://api.beta.wuxiamagic.com/books/6
             */

            private String self;

            public String getSelf() {
                return self;
            }

            public void setSelf(String self) {
                this.self = self;
            }
        }
    }
}
