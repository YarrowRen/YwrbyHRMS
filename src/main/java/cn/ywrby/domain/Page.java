package cn.ywrby.domain;

import com.github.pagehelper.PageHelper;

public class Page {
    int start;  //开始数据的索引
    int count;  //每页数量
    int total;  //总数据量


    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    /**
     * 提供一个构造方法
     */
    public Page(int start,int count){
        super();
        this.start = start;
        this.count = count;
    }
    /**
     * 判断是否有上一页
     */
    public boolean isHasPreviouse(){
        if(start == 0)
            return false;
        return true;
    }
    /**
     * 判断是否有下一页
     */
    public boolean isHasNext(){
        if (start == getLast())
            return false;
        return true;
    }
    /**
     * 计算得到的总页数
     */
    public int getTotalPage(){
        int totalPages;
        if( 0 == total % count ){   //举个栗子  total = 20  count =10   total % count = 0 正好除尽 无余数 进入if
            totalPages = total / count;  // totalPages = 20/10 =2      一共就是两页啦
        }else{//再举个栗子  total = 21  count =10   total % count = 1 没除尽 余数为1  进入else
            totalPages = total / count +1;   // totalPages = （21/10） +1  = 2 + 1 =3   一共就是3页  前两页每页10条数据 最后一页一条数据
        }
        //这种是特殊情况  total = 0  count =10  total % count = 0
        //totalPages = 0/10 = 0   但是第一页不能为 0 呀 那就设值为 1 啦
        if ( 0 == totalPages){
            totalPages = 1; //设值为 1
        }
        return totalPages;  //返回总页数
    }
    /**
     * 计算尾页的第一条数据索引
     */
    public int getLast(){
        //这里指尾页的第一条数据索引，非页数 比如有36条数据 一共4页 最后一页的第一条数据索引就是 30
        // (注意 ：这里是按第一页第一条从0开始计算)
        int last;
        if( 0 == total % count ){  //举个栗子  total = 30  count =10   total % count = 0 正好除尽 无余数 进入if
            last = total - count;   // last = 30 - 10   最后一页第一条索引为 20
        }else{ //再举个栗子  total = 36  count =10   total % count = 6 没除尽 余数为6  进入else
            last = total - total % count ; // last = 36 - （36%10）=36-6 = 30   最后一页第一条索引为 30
        }
        //这种是特殊情况 total = 0  count =10  total % count = 0
        //last = total - count =0 -10 = -10
        // last<0  但是索引不能是负数  那就设为 0
        last = last<0 ? 0:last;
        return last; //返回尾页的第一条数据索引
    }
}

