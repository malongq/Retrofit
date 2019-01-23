package com.izk.retrofit;

import java.util.List;

/**
 * Created by Malong
 * on 19/1/22.
 */
public class PostInfo {

    /**
     * reason : 成功的返回
     * result : {"stat":"1","data":[{"uniquekey":"6c4caa0c3ba6e05e2a272892af43c00e","title":"杨幂的发际线再也回不去了么？网友吐槽像半秃","date":"2017-01-05 11:03","category":"yule","author_name":"腾讯娱乐","url":"http://mini.eastday.com/mobile/170105110355287.html?qid=juheshuju"}]}
     */

    private String reason;
    private ResultBean result;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * stat : 1
         * data : [{"uniquekey":"6c4caa0c3ba6e05e2a272892af43c00e","title":"杨幂的发际线再也回不去了么？网友吐槽像半秃","date":"2017-01-05 11:03","category":"yule","author_name":"腾讯娱乐","url":"http://mini.eastday.com/mobile/170105110355287.html?qid=juheshuju"}]
         */

        private String stat;
        private List<DataBean> data;

        public String getStat() {
            return stat;
        }

        public void setStat(String stat) {
            this.stat = stat;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * uniquekey : 6c4caa0c3ba6e05e2a272892af43c00e
             * title : 杨幂的发际线再也回不去了么？网友吐槽像半秃
             * date : 2017-01-05 11:03
             * category : yule
             * author_name : 腾讯娱乐
             * url : http://mini.eastday.com/mobile/170105110355287.html?qid=juheshuju
             */

            private String uniquekey;
            private String title;
            private String date;
            private String category;
            private String author_name;
            private String url;

            public String getUniquekey() {
                return uniquekey;
            }

            public void setUniquekey(String uniquekey) {
                this.uniquekey = uniquekey;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getCategory() {
                return category;
            }

            public void setCategory(String category) {
                this.category = category;
            }

            public String getAuthor_name() {
                return author_name;
            }

            public void setAuthor_name(String author_name) {
                this.author_name = author_name;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
