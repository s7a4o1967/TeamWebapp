package com.example.service.impl;

import com.example.dto.RegisterDto;
import com.example.entity.LoginEntity;
import com.example.entity.RegisterEntity;
import com.example.exception.CustomException;
import com.example.repository.RegisterRepository;
import com.example.repository.LoginRepository;
import com.example.service.RegisterService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImpl implements RegisterService{
    @Autowired
    private RegisterRepository registerRepository;
    @Autowired
    LoginRepository loginRepository;
    private RegisterEntity registerEntity;
    private ModelMapper modelMapper=new ModelMapper();

    @Override
    public boolean register(RegisterDto registerDto) throws CustomException {
        RegisterEntity registerEntity=null;
        RegisterEntity registerentity=null;
        LoginEntity loginEntity2=null;
        RegisterEntity registerEntity1=registerRepository.findByEmpId(registerDto.getEmpId());
        if(registerEntity1!=null){


            throw new CustomException("User already exists",HttpStatus.CONFLICT);
        }
        else{

            registerEntity=modelMapper.map(registerDto,RegisterEntity.class);
            registerentity = registerRepository.save(registerEntity);
            LoginEntity loginEntity1=modelMapper.map(registerDto,LoginEntity.class);
            loginEntity2=loginRepository.save(loginEntity1);
        }
        return true;
    }


}