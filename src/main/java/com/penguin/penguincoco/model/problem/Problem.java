package com.penguin.penguincoco.model.problem;

import com.penguin.penguincoco.model.base.BaseEntity;
import com.penguin.penguincoco.model.copy.Copy;
import com.penguin.penguincoco.model.course.Course;
import com.penguin.penguincoco.model.judge.Judge;
import com.penguin.penguincoco.model.student.Student;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@TypeDefs({
        @TypeDef(name = "json", typeClass = JsonStringType.class),
})
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Problem extends BaseEntity {

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, optional = false)
    @JoinColumn(name = "course_id")
    private Course course;
    private String name;
    @Enumerated(EnumType.STRING)
    private com.penguin.penguincoco.model.problem.Type type;
    @Enumerated(EnumType.STRING)
    private Category category;
    @Type(type = "json")
    @Column(columnDefinition = "json")
    private List<Language> languages;
    @Type(type = "json")
    @Column(columnDefinition = "json")
    private List<Tag> tags;
    @Type(type = "json")
    @Column(columnDefinition = "json")
    private List<String> patterns;
    private double rate;
    private String description;
    private String inputDesc;
    private String outputDesc;
    @Type(type = "json")
    @Column(columnDefinition = "json")
    private List<TestCase> testCases;
    private Date deadline;
    private int correctNum;
    private int incorrectNum;
    private double correctRate;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "best_student_id")
    private Student bestStudent;
    @OneToMany(mappedBy = "problem", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Copy> copies;
    @OneToMany(mappedBy = "problem", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Judge> judges;

}