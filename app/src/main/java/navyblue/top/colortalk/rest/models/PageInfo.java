package navyblue.top.colortalk.rest.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by CIR on 16/1/25.
 */
public class PageInfo {

    /**
     * total : 14
     * count : 0
     * per_page : 10
     * current_page : 2
     * total_pages : 2
     * links : {"previous":"http://colortalk.navyblue.top/api/moments?page=1"}
     */

    @SerializedName("pagination")
    private PaginationEntity pagination;

    public void setPagination(PaginationEntity pagination) {
        this.pagination = pagination;
    }

    public PaginationEntity getPagination() {
        return pagination;
    }

    public static class PaginationEntity {
        @SerializedName("total")
        private int total;
        @SerializedName("count")
        private int count;
        @SerializedName("per_page")
        private int perPage;
        @SerializedName("current_page")
        private int currentPage;
        @SerializedName("total_pages")
        private int totalPages;
        /**
         * previous : http://colortalk.navyblue.top/api/moments?page=1
         */

        @SerializedName("links")
        private LinksEntity links;

        public void setTotal(int total) {
            this.total = total;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public void setPerPage(int perPage) {
            this.perPage = perPage;
        }

        public void setCurrentPage(int currentPage) {
            this.currentPage = currentPage;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }

        public void setLinks(LinksEntity links) {
            this.links = links;
        }

        public int getTotal() {
            return total;
        }

        public int getCount() {
            return count;
        }

        public int getPerPage() {
            return perPage;
        }

        public int getCurrentPage() {
            return currentPage;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public LinksEntity getLinks() {
            return links;
        }

        public static class LinksEntity {
            @SerializedName("previous")
            private String previous;

            public void setPrevious(String previous) {
                this.previous = previous;
            }

            public String getPrevious() {
                return previous;
            }
        }
    }
}
