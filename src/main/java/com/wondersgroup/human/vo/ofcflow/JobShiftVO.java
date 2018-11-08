package com.wondersgroup.human.vo.ofcflow;

import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.human.bo.ofcflow.JobShift;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JobShiftVO {

    private String id;

    private String name;

    private String cardNo;

    private String sex;

    private String jobChangeType;

    private String remark;

    private Date postChangeDate;

    private String formerPostName;

    private String newPostName;

    public JobShiftVO(JobShift jobShift){
        this.id=jobShift.getId();
        this.name=jobShift.getServant().getName();
        this.cardNo =jobShift.getServant().getCardNo();
        this.sex=jobShift.getServant().getSex().getName();
        this.jobChangeType=jobShift.getPostTenureChange().getName();
        this.remark=jobShift.getRemark();
        this.postChangeDate=jobShift.getCreateTime();
        this.formerPostName=jobShift.getFormerPostName();
        this.newPostName=jobShift.getNewPostCode().getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getJobChangeType() {
        return jobChangeType;
    }

    public void setJobChangeType(String jobChangeType) {
        this.jobChangeType = jobChangeType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPostChangeDate() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(postChangeDate);
    }

    public void setPostChangeDate(Date postChangeDate) {
        this.postChangeDate = postChangeDate;
    }

    public String getFormerPostName() {
        return formerPostName;
    }

    public void setFormerPostName(String formerPostName) {
        this.formerPostName = formerPostName;
    }

    public String getNewPostName() {
        return newPostName;
    }

    public void setNewPostName(String newPostName) {
        this.newPostName = newPostName;
    }


    /**
     *
     * @Title: AssessmentDetail2VO
     * @Description: 类转vo类方法
     * @return
     * @return: List<AssessmentDetailVO>
     */
    public static Page<JobShiftVO> JobShift2VO(Page<JobShift> page) {
        List<JobShiftVO> listVO = new ArrayList<>();
        for (JobShift jobShift : page.getResult()) {
            JobShiftVO vo = new JobShiftVO(jobShift);
            listVO.add(vo);
        }
        Page<JobShiftVO> pageVO=new Page<>(page.getStart(),page.getCurrentPageSize(),page.getTotalSize(),page.getPageSize(),listVO);
        return pageVO;
    }
}
