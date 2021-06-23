package com.qf.service.impl;

import com.qf.bean.TblTodo;
import com.qf.mapper.TblTodoMapper;
import com.qf.service.bean.TblTodoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 待办事项 服务实现类
 * </p>
 *
 * @author qinfeng
 * @since 2021-06-23
 */
@Service
public class TblTodoServiceImpl extends ServiceImpl<TblTodoMapper, TblTodo> implements TblTodoService {

}
