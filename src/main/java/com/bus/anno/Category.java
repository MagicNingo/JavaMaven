package com.bus.anno;

@Table("category")
@QueryType(QuerySqlType.select)
public class Category {
    @Column
    private Integer categoryid;
    @Column
    private String categoryName;
    @Column
    private String categoryDesc;

    /**
     * @return categoryID
     */
    public Integer getCategoryid() {
        return categoryid;
    }

    /**
     * @param categoryid
     */
    public void setCategoryid(Integer categoryid) {
        this.categoryid = categoryid;
    }

    /**
     * @return category_name
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * @param categoryName
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     * @return category_desc
     */
    public String getCategoryDesc() {
        return categoryDesc;
    }

    /**
     * @param categoryDesc
     */
    public void setCategoryDesc(String categoryDesc) {
        this.categoryDesc = categoryDesc;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryid=" + categoryid +
                ", categoryName='" + categoryName + '\'' +
                ", categoryDesc='" + categoryDesc + '\'' +
                '}';
    }
}