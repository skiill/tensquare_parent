package com.tenspuare.spit.controller;


import com.tenspuare.spit.pojo.Spit;
import com.tenspuare.spit.service.SpitService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/spit")
public class SpitController {

    @Autowired
    private SpitService spitService;

    @Autowired
    private RedisTemplate redisTemplate;


    @RequestMapping(method = RequestMethod.GET)
    public Result findAll(){
        return new Result(true, StatusCode.OK,"查询成功",spitService.findAll());
    }

    @RequestMapping(value = "/{spitId}",method = RequestMethod.GET)
    public Result findById(@PathVariable String spitId){
        return new Result(true, StatusCode.OK,"查询成功",spitService.findById(spitId));
    }

    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Spit spit){
        spitService.save(spit);
        return new Result(true, StatusCode.OK,"保存成功");
    }

    @RequestMapping(value = "/{spitId}",method = RequestMethod.PUT)
    public Result save(@PathVariable String spitId,@RequestBody Spit spit){
        spit.set_id(spitId);
        spitService.update(spit);
        return new Result(true, StatusCode.OK,"更新成功");
    }

    @RequestMapping(value = "/{spitId}",method = RequestMethod.DELETE)
    public Result delete(@PathVariable String spitId){
        spitService.deleteById(spitId);
        return new Result(true, StatusCode.OK,"删除成功");
    }
    @RequestMapping(value = "/comment/{parentid}/{page}/{size}",method = RequestMethod.GET)
    public Result findByParentid(@PathVariable String parentid,@PathVariable int page,@PathVariable int size){
        Page<Spit> byParentid = spitService.findByParentid(parentid, page, size);
        return new Result(true, StatusCode.OK,"查询成功",new PageResult<Spit>(byParentid.getTotalElements(),byParentid.getContent()));
    }

    @RequestMapping(value = "/thumbup/{spitid}",method = RequestMethod.PUT)
    public Result thumbup(@PathVariable String spitid){
        //判断当前用户是否已经点赞，还没做认证，先写死id
        String userId = "111";

        if((redisTemplate.opsForValue().get("thumbup_"+userId))!= null){
            return new Result(false,StatusCode.PEP_ERROR,"不能重复点赞");
        }
        spitService.thumbup(spitid);
        redisTemplate.opsForValue().set("thumbup_"+userId,1);
        return new Result(true, StatusCode.OK,"点赞成功");
    }



}
