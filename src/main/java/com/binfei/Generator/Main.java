package com.binfei.Generator;

import com.binfei.Generator.entity.EntityDto;
import com.binfei.Generator.util.DBHelpInfo;
import com.binfei.Generator.util.GenHelpInfo;

/**
 * nickName:binfeiJiang
 * email:874008815@qq.com
 * github:https://github.com/bigbeef
 */

public class Main {


    public static void main(String[] args) throws Exception {
        EntityDto entityDto = DBHelpInfo.genSetEntityDto();
        GenHelpInfo.genFile("DtoTemplate.vm","entity",entityDto.getClassName()+".java","成功生成实体文件",entityDto);
        GenHelpInfo.genFile("DtoBaseMapperTemplate.vm","mapper",entityDto.getClassName()+"BaseMapper.java","成功生成BaseMapper文件",entityDto);
        GenHelpInfo.genFile("DtoMapperTemplate.vm","mapper",entityDto.getClassName()+"Mapper.java","成功生成Mapper",entityDto);
        GenHelpInfo.genFile("DtoBaseMapperXMLTemplate.vm","mapperXml",entityDto.getClassName()+"BaseMapper.xml","成功生成BaseMapper.XML文件",entityDto);
        GenHelpInfo.genFile("DtoMapperXMLTemplate.vm","mapperXml",entityDto.getClassName()+"Mapper.xml","成功生成Mapper.XML文件",entityDto);
        GenHelpInfo.genFile("ServiceTemplate.vm","service",entityDto.getClassName()+"Service.java","成功生成Service.java文件",entityDto);
        GenHelpInfo.genFile("ServiceImplTemplate.vm","service",entityDto.getClassName()+"ServiceImpl.java","成功生成ServiceImpl.java文件",entityDto);

    }


}
