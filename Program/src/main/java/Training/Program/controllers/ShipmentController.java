package Training.Program.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;




import Training.Program.models.Shipments;
import Training.Program.models.Users;
import Training.Program.mongodb.Mongodb;



@Controller
@RequestMapping(path = "/api")
public class ShipmentController {

    private Users user = null;

    @GetMapping(path = "login")
    public String viewLoginPage(Model model){
        if(this.user != null)
            return "redirect:/api/dashboard";
        model.addAttribute("user", new Users());
        return "login";
    }

    @PostMapping(path = "login")
    public String signInUser(@ModelAttribute Users user, Model model){
        if(this.user != null)
            return "redirect:/api/dashboard";
        try{
            Mongodb.authenticateUser(user.getUserName(), user.getPassword());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("user", new Users());
            return "login";
        }
        this.user = user;
        return "redirect:/api/dashboard";
    }
    
    
    @GetMapping(path = "settings")
    public String viewSetting(Model model){
    
   
        return "settings";
    }

    @GetMapping(path = "dashboard")
    public String viewDashboard(Model model){
        if(this.user == null)
            return "redirect:/api/login";
        else
            model.addAttribute("user", this.user);
            return "dashboard";
    }

    @GetMapping(path = "logout")
    public String logoutUser(){
        if(this.user != null)
            this.user = null;
        return "redirect:/api/login";
    }

    @GetMapping(path="devices")
    public String getDevicesDataStream(Model model){
        if(this.user == null)
            return "redirect:/api/login";
        else{
            model.addAttribute("devices", Mongodb.getDevices());
            return "deviceDataStream";
        }
    }

    @GetMapping(path = "createShipment")
    public String createShipmentPage(Model model){
        if(this.user == null)
            return "redirect:/api/login";
        else{
            model.addAttribute("shipment", new Shipments());
            model.addAttribute("devices", Mongodb.getDeviceIDs());
            return "createShipment";
        }
    }

    @PostMapping(path = "createShipment")
    public String submitShipmentForm(@ModelAttribute Shipments shipment, Model model){
        if(this.user == null)
            return "redirect:/api/login";
        else{
            model.addAttribute("devices", Mongodb.getDeviceIDs());
            try {
            	notEmpty(shipment.getInvoiceNumber(), "Enter Shipment Number!");
                notEmpty(shipment.getContainerNumber(), "Enter Container Number!");
                notEmpty(shipment.getShipmentDescription(), "Provide a description!");
                notEmpty(shipment.getRouteDetail(), "Select a Route!");
                notEmpty(shipment.getGoodsType(), "Select Goods!");
                notEmpty(shipment.getDevice(), "Select Device!");
                notEmpty(shipment.getExpectedDeliverydate(), "Enter Date!");
                notEmpty(shipment.getPoNumber(), "Enter PO Number!");
                notEmpty(shipment.getDeliveryNumber(), "Enter Delivery Number!");
                notEmpty(shipment.getNdcNumber(), "Enter NDC Number!");
                notEmpty(shipment.getBatchId(), "Enter Batch ID!");
                notEmpty(shipment.getSerialNumber(), "Enter Serial Number!");
                Mongodb.addShipment(this.user.getUserName(), shipment.getInvoiceNumber(), shipment.getContainerNumber(),
                        shipment.getShipmentDescription(), shipment.getRouteDetail(), shipment.getGoodsType(), shipment.getDevice(), shipment.getExpectedDeliverydate(), shipment.getPoNumber(), shipment.getDeliveryNumber(),
                        shipment.getNdcNumber(), shipment.getBatchId(), shipment.getSerialNumber());    }
            catch (Exception e) {
                model.addAttribute("error", e.getMessage());
                model.addAttribute("shipment", shipment);
                return "createShipment";
            }
            model.addAttribute("success", "Submitted successfully!");
            model.addAttribute("shipment", new Shipments());
            return "createShipment";
        }
    }

  

    private static boolean notEmpty(String s, String msg) throws Exception{
        if(s.equals(""))
            throw new Exception(msg);
        return true;
    }

}