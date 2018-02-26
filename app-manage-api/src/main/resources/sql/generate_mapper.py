#!/usr/bin/env python
# -*- encoding: utf-8 -*-
"""
通过一个schema.sql来生成标准表结构的Domain和基础Mapper
基于mybatis-plus库使用

使用方法2种：

1. python generate_mapper.py src_base_dir domain_package mapper_package schema_name author
2. 配置好下面的参数后直接运行此文件 python generate_mapper.py

参数：
1. src_base_dir     源码基础路径
2. domain_package   domain类的包名
3. mapper_package   mapper类的包名
4. schema_name      schema sql文件的绝对路径
5. author           源代码的作者：熊能
"""

###################################下面的参数你来指定###########################
src_base_dir = r'E:\enzhico\app-manage\src\main\java'
domain_package = r'com.enzhico.jwt.common.dao.entity'
mapper_package = r'com.enzhico.jwt.common.dao.repository'
schema_name = r'E:\enzhico\app-manage\src\main\resources\sql\schema.sql'
author = r'熊能'
TODAY_STR = '2018/01/02'
###############################################################################

import sys
import os
import datetime
import shutil

# 基类功能列
BASE_FIELS = {}

# MySQL type to java type
MYSQL_TYPE_MAP = {
    'BIT(1)': ('Boolean',)
    , 'BIT': ('byte[]',)
    , 'TINYINT': ('Integer',)
    , 'BOOLEAN': ('Boolean',)
    , 'BOOL': ('Boolean',)
    , 'SMALLINT': ('Integer',)
    , 'MEDIUMINT': ('Integer',)
    , 'INT': ('Integer',)
    , 'INTEGER': ('Integer',)
    , 'BIGINT': ('Long',)
    , 'FLOAT': ('Float',)
    , 'DOUBLE': ('Double',)
    , 'DECIMAL': ('BigDecimal', 'java.math.BigDecimal')
    , 'DATE': ('Date', 'java.util.Date')
    , 'DATETIME': ('Date', 'java.util.Date')
    , 'TIMESTAMP': ('Date', 'java.util.Date')
    , 'TIME': ('Date', 'java.util.Date')
    , 'CHAR': ('String ',)
    , 'VARCHAR': ('String',)
    , 'BINARY': ('byte[]',)
    , 'VARBINARY': ('byte[]',)
    , 'TINYBLOB': ('byte[]',)
    , 'TINYTEXT': ('String',)
    , 'BLOB': ('byte[]',)
    , 'TEXT': ('String',)
    , 'MEDIUMBLOB': ('byte[]',)
    , 'MEDIUMTEXT': ('String',)
    , 'LONGBLOB': ('byte[]',)
    , 'LONGTEXT': ('String',)
    , 'ENUM': ('String',)
    , 'SET': ('String',)
    , 'bit(1)': ('Boolean',)
    , 'bit': ('byte[]',)
    , 'tinyint': ('Integer',)
    , 'boolean': ('Boolean',)
    , 'bool': ('Boolean',)
    , 'smallint': ('Integer',)
    , 'mediumint': ('Integer',)
    , 'int': ('Integer',)
    , 'integer': ('Integer',)
    , 'bigint': ('Long',)
    , 'float': ('Float',)
    , 'double': ('Double',)
    , 'decimal': ('BigDecimal', 'java.math.BigDecimal')
    , 'date': ('Date', 'java.util.Date')
    , 'datetime': ('Date', 'java.util.Date')
    , 'timestamp': ('Date', 'java.util.Date')
    , 'time': ('Date', 'java.util.Date')
    , 'char': ('String ',)
    , 'varchar': ('String',)
    , 'binary': ('byte[]',)
    , 'varbinary': ('byte[]',)
    , 'tinyblob': ('byte[]',)
    , 'tinytext': ('String',)
    , 'blob': ('byte[]',)
    , 'text': ('String',)
    , 'mediumblob': ('byte[]',)
    , 'mediumtext': ('String',)
    , 'longblob': ('byte[]',)
    , 'longtext': ('String',)
    , 'enum': ('String',)
    , 'set': ('String',)
}


def camel_to_underline(camel_format):
    """
    驼峰命名格式转下划线命名格式
    """
    return ''.join([s if s.islower() else '_' + s.lower() for s in camel_format])[1:]


def underline_to_camel(underline_format, is_field=False):
    """
    下划线命名格式驼峰命名格式
    """
    try:
        result = ''.join([s.capitalize() for s in underline_format.split('_')])
    except:
        print(underline_format + "...error...")
    return result[0].lower() + result[1:] if is_field else result


def load_schema(filename):
    """先加载schema.sql文件来获取所有建表语句"""
    result = []
    with open(filename, encoding='utf-8') as sqlfile:
        each_table = []  # 每张表定义
        for line in sqlfile:
            linestrip = line.strip()
            if not linestrip or linestrip.startswith("#") \
                    or linestrip.startswith("INDEX") or linestrip.startswith("ALTER TABLE"):
                continue
            line = line.replace("`", "")
            if line.startswith('--'):
                temp_comment = line.split('--')[1].strip()
            elif 'DROP TABLE' in line:
                each_table.insert(0, temp_comment)
                each_table.insert(1, line.strip().split()[-1][:-1])
            elif ' COMMENT ' in line and 'ENGINE=' not in line:
                col_arr = line.split()
                col_name = col_arr[0]
                col_type = col_arr[1]
                if 'PRIMARY KEY' in line or 'NOT NULL' in line:
                    col_null = 'NOT NULL'
                else:
                    col_null = ''
                col_remark = line.split(' COMMENT ')
                cr = col_remark[-1].strip().replace("'", "")
                each_table.append((col_name, col_type, col_null, cr[:-1] if cr.endswith(',') else cr))
            elif 'ENGINE=' in line:
                # 单个表定义结束
                result.append(list(each_table))
                each_table.clear()
    return result


def write_beans(src_base_dir, domain_package, mapper_package, schema_name, author):

    beans_dir = os.path.join(src_base_dir, domain_package.replace('.', os.sep))
    mapper_dir = os.path.join(src_base_dir, mapper_package.replace('.', os.sep))

    if not os.path.exists(beans_dir):
        os.makedirs(beans_dir)
    if not os.path.exists(mapper_dir):
        os.makedirs(mapper_dir)
    shutil.rmtree(beans_dir)
    os.mkdir(beans_dir)
    shutil.rmtree(mapper_dir)
    os.mkdir(mapper_dir)

    domain_package_with_semicolon = domain_package + ";"
    mapper_package_with_semicolon = mapper_package + ";"

    # 今日格式化字符串
    # today_today = datetime.datetime.now().strftime('%Y/%m/%d')
    today_today = TODAY_STR
    today_str = ' * @since {}'.format(today_today)

    table_data = load_schema(schema_name)
    # 然后开始对每个表生成一个Domain类
    for table in table_data:
        table_name_comment = table[0]
        table_name_real = table[1]
        class_name = underline_to_camel(table_name_real[2:])
        lines = []
        lines.append('package ' + domain_package_with_semicolon)
        lines.append('\n')
        lines.append('import com.baomidou.mybatisplus.annotations.TableName;')
        lines.append('import com.baomidou.mybatisplus.enums.IdType;')
        lines.append('import com.baomidou.mybatisplus.annotations.TableId;')
        lines.append('import com.baomidou.mybatisplus.activerecord.Model;')
        lines.append('import java.io.Serializable;')
        lines.append('\n')
        lines.append('/**')
        lines.append(' * ' + table_name_comment)
        lines.append(' *')
        lines.append(' * @author {}'.format(author))
        lines.append(' * @version 1.0')
        lines.append(today_str)
        lines.append(' */')
        lines.append('@TableName(value = "{}")'.format(table_name_real))
        lines.append('public class {} extends Model<{}> {{'.format(class_name, class_name))
        lines.append('\n')
        lines.append('private static final long serialVersionUID = 1L;')
        lines.append('\n')
        lines_fields = []
        lines_methods = []
        other_import = set()
        field_name_list = []

        for each_column in table[2:]:
            # 列名
            column_name = each_column[0]
            if column_name in BASE_FIELS:
                continue
            field_name = underline_to_camel(column_name, is_field=True)
            field_name_list.append(field_name)
            field_name_method = underline_to_camel(column_name)
            # 类型
            ctype = each_column[1]
            java_type_t = MYSQL_TYPE_MAP[ctype.split('(')[0] if ctype != 'BIT(1)' else ctype]
            java_type = java_type_t[0]
            import_str = 'import {};'.format(java_type_t[1]) if len(java_type_t) > 1 else None
            # 空值约束
            column_null = each_column[2]
            # 字段生成
            column_comment = each_column[3]
            lines_fields.append('    /**')
            lines_fields.append('     * {}'.format(column_comment))
            lines_fields.append('     */')
            if column_name == 'id':
                lines_fields.append('    @TableId(value="id", type= IdType.AUTO)')
            lines_fields.append('    private {} {};'.format(java_type, field_name))
            if import_str:
                other_import.add(import_str)

            # get方法生成
            lines_methods.append('    /**')
            lines_methods.append('     * 获取 {}.'.format(column_comment))
            lines_methods.append('     *')
            lines_methods.append('     * @return {}.'.format(column_comment))
            lines_methods.append('     */')
            lines_methods.append('    public {} get{}() {{'.format(java_type, field_name_method))
            lines_methods.append('        return {};'.format(field_name))
            lines_methods.append('    }')
            lines_methods.append('\n')
            # set方法生成
            lines_methods.append('    /**')
            lines_methods.append('     * 设置 {}.'.format(column_comment))
            lines_methods.append('     *')
            lines_methods.append('     * @param {} {}.'.format(field_name, column_comment))
            lines_methods.append('     */')
            lines_methods.append('    public void set{}({} {}) {{'.format(
                field_name_method, java_type, field_name))
            lines_methods.append('        this.{} = {};'.format(field_name, field_name))
            lines_methods.append('    }')
            lines_methods.append('\n')

        for each_other in sorted(other_import):
            lines.insert(2, each_other)
        lines.extend(lines_fields)
        lines.append('\n')
        lines.extend(lines_methods)
        # 最后加上pkVal()实现
        lines.append('    @Override')
        lines.append('    protected Serializable pkVal() {')
        lines.append('        return this.id;')
        lines.append('    }')
        lines.append('\n')
        lines.append('}')

        # 加上换行符
        lines = [line + "\n" if line != '\n' else line for line in lines]
        # 开始写java源文件
        java_file = class_name + '.java'
        with open(os.path.join(beans_dir, java_file), mode='w', encoding='utf-8') as jf:
            jf.writelines(lines)

    # 然后开始对每个表生成一个Mapper类
    for table in table_data:
        table_name_comment = table[0]
        table_name_real = table[1]
        class_name = underline_to_camel(table_name_real[2:])
        lines = []
        lines.append('package ' + mapper_package_with_semicolon)
        lines.append('\n')
        lines.append('import {}.{};'.format(domain_package, class_name))
        lines.append('import com.baomidou.mybatisplus.mapper.BaseMapper;')
        lines.append('\n')
        lines.append('/**')
        lines.append(' * ' + table_name_comment + " Mapper")
        lines.append(' *')
        lines.append(' * @author {}'.format(author))
        lines.append(' * @version 1.0')
        lines.append(today_str)
        lines.append(' */')
        lines.append('public interface {0}Mapper extends BaseMapper<{0}> {{'.format(class_name))
        lines.append('\n')
        lines.append('}')

        lines = [line + "\n" if line != '\n' else line for line in lines]
        # 开始写mapper源文件
        with open(os.path.join(mapper_dir, '{}Mapper.java'.format(class_name)), mode='w', encoding='utf-8') as jf:
            jf.writelines(lines)
    print('successful...')
    pass

if __name__ == '__main__':
    if len(sys.argv) > 4:
        src_base_dir = sys.argv[1]
        domain_package = sys.argv[2]
        mapper_package = sys.argv[3]
        schema_name = sys.argv[4]
        author = sys.argv[5]
    write_beans(src_base_dir, domain_package, mapper_package, schema_name, author)
