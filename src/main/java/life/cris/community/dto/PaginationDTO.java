package life.cris.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PaginationDTO {
    private List<QuestionDTO> questions;
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showEndPage;
    private Integer currentPage;
    private List<Integer> pageNums = new ArrayList<>();
    private Integer totalPage;

    public void setPagination(Integer totalPage, Integer page) {
        this.totalPage = totalPage;
        this.currentPage = page;
        pageNums.add(page);
        for (int i = 1; i <= 3; i++) {
            if (page - i > 0) {
                pageNums.add(0, page - i);
            }
            if (page + i <= totalPage) {
                pageNums.add(page + i);
            }
        }

        //是否展示上一页
        showPrevious = page != 1;
        //是否展示下一页
        showNext = !page.equals(totalPage);
        //是否展示首页
        showFirstPage = !pageNums.contains(1);
        //是否展示末页
        showEndPage = !pageNums.contains(totalPage);
    }
}