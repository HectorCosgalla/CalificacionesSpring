package com.valcos98.schoolproject.groupsComponents;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.valcos98.schoolproject.gradesComponents.GradeModel;
import com.valcos98.schoolproject.gradesComponents.GradeRepository;

@Controller
@RequestMapping("/grupo")
public class GroupController {

    private GroupRepository groupRepository;
    private GradeRepository gradeRepository;

    public GroupController(GroupRepository groupRepository, GradeRepository gradeRepository){
        this.groupRepository = groupRepository;
        this.gradeRepository = gradeRepository;
    }

    @GetMapping("/nuevo")
    public String createANewGroup(Model model){
        List<GradeModel> grades = gradeRepository.findAll();
        GroupModel group = new GroupModel();
        model.addAttribute("grados", grades);
        model.addAttribute("group", group);
        return "/groups_pages/new_group";
    }

    @PostMapping("/nuevo")
    public String saveTheNewGroup(@ModelAttribute GroupModel group){
        groupRepository.save(group);
        return "redirect:/";
    }
}
