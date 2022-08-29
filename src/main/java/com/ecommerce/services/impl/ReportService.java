package com.ecommerce.services.impl;

import com.ecommerce.models.CategoryDetails;
import com.ecommerce.models.Customer;
import com.ecommerce.models.Owner;
import com.ecommerce.models.ProductDetails;
import com.ecommerce.repository.CategoryRepo;
import com.ecommerce.repository.CustomerRepo;
import com.ecommerce.repository.OwnerRepo;
import com.ecommerce.repository.ProductRepo;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService
{
    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private OwnerRepo ownerRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ProductRepo productRepo;
    public String exportReportForCustomer(String reportFormat) throws FileNotFoundException, JRException {

        String path="D:\\Final Project\\Reports";
        List<Customer> customerList=customerRepo.findAll();
        //load file and comile it
        File file= ResourceUtils.getFile("classpath:customers.jrxml");
        JasperReport jasperReport= JasperCompileManager.compileReport(file.getAbsolutePath());

        JRBeanCollectionDataSource dataSource=new JRBeanCollectionDataSource(customerList);

        Map<String, Object> parameter=new HashMap<>();
        parameter.put("created by ","Akash ");
        JasperPrint jasperPrint= JasperFillManager.fillReport(jasperReport,parameter,dataSource);
        if (reportFormat.equalsIgnoreCase("html"))
        {
            JasperExportManager.exportReportToHtmlFile(jasperPrint,path+"\\customers.html");
        }
        if (reportFormat.equalsIgnoreCase("pdf"))
        {
            JasperExportManager.exportReportToPdfFile(jasperPrint,path+"\\customers.pdf");
        }
      return "report generated in path"+path;
    }
    public String exportReportForOwner(String reportFormat) throws FileNotFoundException, JRException {

        String path="D:\\Final Project\\Reports";
        List<Owner>  ownerList=ownerRepo.findAll();
        //load file and comile it
        File file= ResourceUtils.getFile("classpath:owners.jrxml");
        JasperReport jasperReport= JasperCompileManager.compileReport(file.getAbsolutePath());

        JRBeanCollectionDataSource dataSource=new JRBeanCollectionDataSource(ownerList);

        Map<String, Object> parameter=new HashMap<>();
        parameter.put("created by ","Akash ");
        JasperPrint jasperPrint= JasperFillManager.fillReport(jasperReport,parameter,dataSource);
        if (reportFormat.equalsIgnoreCase("html"))
        {
            JasperExportManager.exportReportToHtmlFile(jasperPrint,path+"\\owners.html");
        }
        if (reportFormat.equalsIgnoreCase("pdf"))
        {
            JasperExportManager.exportReportToPdfFile(jasperPrint,path+"\\owners.pdf");
        }
        return "report generated in path"+path;
    }

    public String exportReportForCategory(String reportFormat) throws FileNotFoundException, JRException {

        String path="D:\\Final Project\\Reports";
        List<CategoryDetails> categoryList=categoryRepo.findAll();
        //load file and comile it
        File file= ResourceUtils.getFile("classpath:categories.jrxml");
        JasperReport jasperReport= JasperCompileManager.compileReport(file.getAbsolutePath());

        JRBeanCollectionDataSource dataSource=new JRBeanCollectionDataSource(categoryList);

        Map<String, Object> parameter=new HashMap<>();
        parameter.put("created by ","Akash ");
        JasperPrint jasperPrint= JasperFillManager.fillReport(jasperReport,parameter,dataSource);
        if (reportFormat.equalsIgnoreCase("html"))
        {
            JasperExportManager.exportReportToHtmlFile(jasperPrint,path+"\\categories.html");
        }
        if (reportFormat.equalsIgnoreCase("pdf"))
        {
            JasperExportManager.exportReportToPdfFile(jasperPrint,path+"\\categories.pdf");
        }
        return "report generated in path"+path;
    }
    public String exportReportForProduct(String reportFormat) throws FileNotFoundException, JRException {

        String path="D:\\Final Project\\Reports";
        List<ProductDetails> productList=productRepo.findAll();
        //load file and comile it
        File file= ResourceUtils.getFile("classpath:customers.jrxml");
        JasperReport jasperReport= JasperCompileManager.compileReport(file.getAbsolutePath());

        JRBeanCollectionDataSource dataSource=new JRBeanCollectionDataSource(productList);

        Map<String, Object> parameter=new HashMap<>();
        parameter.put("created by ","Akash ");
        JasperPrint jasperPrint= JasperFillManager.fillReport(jasperReport,parameter,dataSource);
        if (reportFormat.equalsIgnoreCase("html"))
        {
            JasperExportManager.exportReportToHtmlFile(jasperPrint,path+"\\products.html");
        }
        if (reportFormat.equalsIgnoreCase("pdf"))
        {
            JasperExportManager.exportReportToPdfFile(jasperPrint,path+"\\products.pdf");
        }
        return "report generated in path"+path;
    }
}
