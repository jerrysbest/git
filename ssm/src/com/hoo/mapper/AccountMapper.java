package com.hoo.mapper;
import java.util.*;
import org.apache.ibatis.annotations.Select;
//import org.mybatis.spring.annotation.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import com.hoo.entity.Account;
@MapperScan("mapper") 
public interface AccountMapper extends SqlMapper {
	public List<Account> getAllAccount(); 
	public Account getAccount();   
	public Account getAccountById(String id); 
	public Account getAccountByNames(String spring);  
	@Select("select * from account where username = #{name}")  
	public Account getAccountByName(String name);  
	public void addAccount(Account account);   
	public void editAccount(Account account); 
	public void removeAccount(int id);
}
