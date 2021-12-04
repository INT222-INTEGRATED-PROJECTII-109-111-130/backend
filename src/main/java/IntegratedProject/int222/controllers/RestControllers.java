package IntegratedProject.int222.controllers;

import IntegratedProject.int222.exception.MessageException;
import IntegratedProject.int222.models.*;
import IntegratedProject.int222.repositories.*;
import IntegratedProject.int222.uploadfiles.StorageFileNotFoundException;
import IntegratedProject.int222.uploadfiles.StorageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
public class RestControllers {
    @Autowired
    private AccountsRepository accRepo ;
    @Autowired
    private BrandsRepository brandRepo;
    @Autowired
    private CartRepository cartRepo;
    @Autowired
    private ColorsRepository colorRepo;
    @Autowired
    private ProductcolorRepository prodcolorRepo;
    @Autowired
    private ProductsRepository prodRepo;
    /* ยังไม่ได้ทำ method RESTAPI */
    @Autowired
    private ProductsizeRepository prodsizeRepo;
    @Autowired
    private SizeRepository sizeRepo;
    /* END */
    final StorageService storageService;
    public long idsize;
    public long idprodc;
    public long idacc;
    @Autowired
    public RestControllers(StorageService storageService) {
        this.storageService = storageService;
    }




    @GetMapping("/listfileupload")
    public List<String> listUploadedFiles() throws IOException {
        return storageService.loadAll().map(
                        path -> MvcUriComponentsBuilder.fromMethodName(RestControllers.class,
                                "serveFile", path.getFileName().toString()).build().toUri().toString())
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/files/{filename:.+}", produces = MediaType.IMAGE_JPEG_VALUE )
//    @ResponseBody
    public Resource serveFile(@PathVariable String filename) {
//        Resource file = storageService.loadAsResource(filename);
        return storageService.loadAsResource(filename);
//                ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
//                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

//    @GetMapping(value = "/files" )
//    public Stream<Path> serveAllFile() {
//        return  storageService.loadAll();
//
//    }


    @DeleteMapping(value = "/deletefile/{filename:.+}", produces = MediaType.IMAGE_JPEG_VALUE )
    public void deleteFile(@PathVariable String filename) throws IOException {
        storageService.delete(filename);
    }

    // method upload
    @PostMapping("/upload")
    public void handleFileUpload(@RequestParam("file") MultipartFile file) {
        storageService.store(file);
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }
    /* GET */
    @GetMapping("/showallaccount")
    public List<accounts> showAcc() throws RuntimeException {
        return  accRepo.findAll();
    }

    @GetMapping("/showallbrand")
    public List<brands> showBrand() {
        return  brandRepo.findAll();
    }

    @GetMapping("/showcart/{id}")
    public cart[] showCart(@PathVariable long id) {
        if( cartRepo.findAllByAccountId(id) == null){
            throw  new MessageException("id : "+ id + " does not have product in cart !!");
        }
        return  cartRepo.findAllByAccountId(id);
    }

    @GetMapping("/showallcolor")
    public List<colors> showColor() {
        return  colorRepo.findAll();
    }

    @GetMapping("/showallsize")
    public List<size> showSize(){
        return sizeRepo.findAll();
    }

    @GetMapping("/showallprodcolor")
    public List<productcolor> showProdColor() {
        System.out.println(prodcolorRepo.findAll().size());
        List<productcolor> allPC = prodcolorRepo.findAll();
        System.out.println(allPC.get(prodcolorRepo.findAll().size()-1).getProductcolorId());
        return  prodcolorRepo.findAll();
    }
    @GetMapping("/showallproduct")
    public List<products> showProd() {
        return  prodRepo.findAll();
    }

    @GetMapping("/show1prod/{id}")
    public products s1pord(@PathVariable long id){
        if (prodRepo.findById(id).orElse(null) == null ){
            throw  new MessageException("id: "+ id + " does not exist !!");
        }
        products p =  prodRepo.findById(id).get();
        return p;
    }

    @GetMapping("/1acc/{id}")
    public accounts s1a(@PathVariable long id){
        if (accRepo.findById(id).orElse(null) == null ){
            throw  new MessageException("id: "+ id + " does not exist !!");
        }
        accounts p =  accRepo.findById(id).get();
        return p;
    }

    /* END */
    /* POST */

//    @PostMapping("/register")
//    public void register(@RequestParam("account") String[] account){
//
//    }

    @PostMapping("/addbrand")
    public void addBrand(@RequestBody brands bname) {
        bname.setBrandId(bname.getBrandId()== 1 ?
                brandRepo.findAll().size()-1 == -1? 1:
                        brandRepo.findAll().get(brandRepo.findAll().size()-1).getBrandId()+1 : 1);
        brandRepo.save(bname);
    }

    @PostMapping("/addcolor")
    public void addColor(@RequestBody colors color) {
        color.setColorId(color.getColorId() == 1 ?
                colorRepo.findAll().size()-1 == -1? 1:
                        colorRepo.findAll().get(colorRepo.findAll().size()-1).getColorId()+1 : 1);
        colorRepo.save(color);
    }

    /* ยังไม่เสร็จ เช็คเงื่อนไขว่มีซ้ำไหม */
//    @PostMapping("/addaccount")
//    public void addAccount(@RequestBody accounts acc) {
//
//
//        if(accRepo.findByEmail(acc.getEmail()).orElse(null) != null && accRepo.findByEmail(acc.getEmail()).orElse(null).getEmail() == acc.getEmail()  ){
//            throw  new MessageException("Is have already email exist");
//        }else {
//
//            this.idacc = accRepo.findAll().size()-1 == -1? 300001: accRepo.findAll().get(accRepo.findAll().size()-1).getAccountId()+1;
//            accounts accnew = new accounts(this.idacc,acc.getFirstName(),acc.getLastName(),acc.getEmail(),acc.getPassword(),acc.getAccountRole());
//
//            accRepo.save(accnew);
//        };
//    }
    /* END */

    @PostMapping("/addcart")
    public void addCart(@RequestBody cart cart) {
        cart.setCartId(cart.getCartId() == 1 ?
                cartRepo.findAll().size()-1 == -1? 500001:
                        cartRepo.findAll().get(cartRepo.findAll().size()-1).getCartId()+1 :500001);
        cartRepo.save(cart);
    }

    @PostMapping("/addprodcolor")
    public void addProdColor(@RequestBody productcolor prodColor) {
        prodcolorRepo.save(prodColor);
    }



    @PostMapping("/addprod")
    public void addProd(@RequestParam("prod") String produc
                        ,@RequestParam("prodcolor") String[] prodcolor
                        ,@RequestParam("file") MultipartFile file
                        ,@RequestParam("size") String[] size){
        products product ;
        try {
            storageService.store(file);
            ObjectMapper objectMapper = new ObjectMapper();
            product = objectMapper.readValue(produc, products.class);
            product.setProductId(prodRepo.findAll().get(prodRepo.findAll().size()-1).getProductId()+1);
            prodRepo.save(product);
            for (int i = 0; i < prodcolor.length; i = ++i) {
                this.idprodc = prodcolorRepo.findAll().size()-1 == -1? 700001: prodcolorRepo.findAll().get(prodcolorRepo.findAll().size()-1).getProductcolorId()+1;
                productcolor productcolor = new productcolor(this.idprodc,product.getProductId(),Long.parseLong(prodcolor[i]));
                prodcolorRepo.save(productcolor);
            }

            for (int i = 0; i < size.length; i = ++i) {
                this.idsize = prodsizeRepo.findAll().size()-1 == -1? 900001: prodsizeRepo.findAll().get(prodsizeRepo.findAll().size()-1).getProductsizeId()+1;
                productsize products = new productsize(this.idsize,product.getProductId(),Long.parseLong(size[i]));
                prodsizeRepo.save(products);
            }
        }catch (IOException err){
            throw  new MessageException(err.getMessage());
        }
    }




    /* END*/

    /* ยังไม่ได้test */
        /* PUT */
        @PutMapping("/updateaccount")
        public void updateAccount(@RequestBody accounts acc){
            accounts account = accRepo.findById(acc.getAccountId()).orElse(null);
            if(account != null){
                account.setFirstName(acc.getFirstName());
                account.setLastName(acc.getLastName());
                accRepo.save(account);
            }

        }

        @PutMapping("/updateroleacc")
        public void updateRoleAccount(@RequestBody accounts acc){
            accounts account = accRepo.findById(acc.getAccountId()).orElse(null);
            if(account != null){

                account.setAccountRole(acc.getAccountRole());
                accRepo.save(account);
            }

        }


        /*method เปลี่ยน pass */

        @PutMapping("/updatebrand")
        public void updateBrand(@RequestBody brands b){
            brands brand = brandRepo.findById(b.getBrandId()).orElse(null);
            if(brand != null){
                brand.setBrandName(b.getBrandName());
                brandRepo.save(brand);
            }
        }

        @PutMapping("/editprod")
        @Transactional
        public void editProd(@RequestParam("prod") String produc
                ,@RequestParam("prodcolor") String[] prodcolornew
                ,@RequestParam("file") MultipartFile file
                ,@RequestParam("size") String[] sizenew){
            products editproduct ;
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                editproduct = objectMapper.readValue(produc, products.class);
                //product.setProductId(prodRepo.findAll().get(prodRepo.findAll().size()-1).getProductId()+1);
                //prodRepo.save(product);

                products haveproduct = prodRepo.findById(editproduct.getProductId()).orElse(null);
                if(haveproduct != null){
                    /*edit image*/
                    System.out.println(haveproduct.getProductImage());
                    System.out.println(file.getOriginalFilename());
                    if(haveproduct.getProductImage() != file.getOriginalFilename()) {

                        storageService.delete(haveproduct.getProductImage());
                        storageService.store(file);
                    }
                    /*edit product*/
                    haveproduct.setProductName(editproduct.getProductName());
                    haveproduct.setProductDescription(editproduct.getProductDescription());
                    haveproduct.setOnsaleDate(editproduct.getOnsaleDate());
                    haveproduct.setProductPrice(editproduct.getProductPrice());
                    haveproduct.setProductImage(editproduct.getProductImage());
                    haveproduct.setBrandId(editproduct.getBrandId());
                    prodRepo.save(haveproduct);

                    productcolor[] prodColor = prodcolorRepo.findAllByProductId(haveproduct.getProductId());
                    int lengthcolor = prodColor.length;
                    for (int i = 0; i < lengthcolor; i++) {
                        System.out.println(prodColor[0].getProductId());
                        prodcolorRepo.deleteByProductId(prodColor[0].getProductId());
                    }
                    productsize[] sizeold = prodsizeRepo.findAllByProductId(haveproduct.getProductId());
                    int lengthsize = sizeold.length;
                    for (int i = 0; i < lengthsize; i++) {
                        prodsizeRepo.deleteByProductId(sizeold[0].getProductId());
                    }
                    for (int i = 0; i < prodcolornew.length; i = ++i) {
                        this.idprodc = prodcolorRepo.findAll().size()-1 == -1? 700001: prodcolorRepo.findAll().get(prodcolorRepo.findAll().size()-1).getProductcolorId()+1;
                        productcolor productcolor = new productcolor(this.idprodc,haveproduct.getProductId(),Long.parseLong(prodcolornew[i]));
                        prodcolorRepo.save(productcolor);
                    }
                    for (int i = 0; i < sizenew.length; i = ++i) {
                        this.idsize = prodsizeRepo.findAll().size()-1 == -1? 900001: prodsizeRepo.findAll().get(prodsizeRepo.findAll().size()-1).getProductsizeId()+1;
                        productsize products = new productsize(this.idsize,haveproduct.getProductId(),Long.parseLong(sizenew[i]));
                        prodsizeRepo.save(products);
                    }
                    /*edit productcolor*/
//                    if(prodColor.length == prodcolornew.length){
//                        for (int i = 0; i < prodColor.length; i++) {
//                            prodColor[i].setColorId(Long.parseLong(prodcolornew[i]));
//                            prodcolorRepo.save(prodColor[i]);
//                        }
//                    }else if(prodColor.length > prodcolornew.length){
//                        int diff = prodColor.length - prodcolornew.length;
//                        for (int j = 0; j < diff; j++) {
//                            prodcolorRepo.deleteById(prodColor[prodColor.length - 1].getProductcolorId());
//                        }
//                        for (int i = 0; i < prodcolornew.length; i++) {
//                            prodColor[i].setColorId(Long.parseLong(prodcolornew[i]));
//                        }
//                    } else if( prodColor.length < prodcolornew.length){
//                        int dif = prodcolornew.length - prodColor.length ;
//                        for (int i = 0; i < prodcolornew.length; i++) {
//                            prodColor[i].setColorId(Long.parseLong(prodcolornew[i]));
//                        }
//                        for (int j = 0; j < dif; j++) {
//                            List<Productcolor> allPC = prodcolorRepo.findAll();
//                            Productcolor prodcolor = new Productcolor(allPC.get(prodcolorRepo.findAll().size()-1).getProductcolorId()+1,prodColor[0].getProductId(),Long.parseLong(prodcolornew[j]));
//                            prodcolorRepo.save(prodcolor);
//                        }
//                    }

                    /*edit productsize*/
//                    Productsize[] sizeold = prodsizeRepo.findAllByProductId(haveproduct.getProductId());
//                    if(sizeold.length == sizenew.length){
//                        for (int i = 0; i < sizeold.length; i++) {
//                            sizeold[i].setProductsizeId(Long.parseLong(sizenew[i]));
//                            prodsizeRepo.save(sizeold[i]);
//                        }
//                    }else if(sizeold.length > sizenew.length){
//                        int diff = sizeold.length - sizenew.length;
//                        for (int j = 0; j < diff; j++) {
//                            prodsizeRepo.deleteById(sizeold[sizeold.length - 1].getProductsizeId());
//                        }
//                        for (int i = 0; i < sizenew.length; i++) {
//                            sizeold[i].setProductsizeId(Long.parseLong(sizenew[i]));
//                        }
//                    } else if( sizeold.length < sizenew.length){
//                        int dif = sizenew.length - sizeold.length ;
//                        for (int i = 0; i < sizenew.length; i++) {
//                            sizeold[i].setProductsizeId(Long.parseLong(sizenew[i]));
//                        }
//                        for (int j = 0; j < dif; j++) {
//                            List<Productsize> allPC = prodsizeRepo.findAll();
//                            Productsize prodsize = new Productsize(allPC.get(prodsizeRepo.findAll().size()-1).getProductsizeId()+1,sizeold[0].getProductId(),Long.parseLong(sizenew[j]));
//                            prodsizeRepo.save(prodsize);
//                        }
//                    }


                }else{
                    throw  new MessageException(" not have product id "+editproduct.getProductId()+" in database");
                }
            }catch (IOException err){
                throw  new MessageException(err.getMessage());
            }

        }

        @PutMapping("/updateprod")
        public void updateProd(@RequestBody products prod) {
            products product = prodRepo.findById(prod.getProductId()).orElse(null);
            if(product != null){
            product.setProductName(prod.getProductName());
            product.setProductDescription(prod.getProductDescription());
            product.setOnsaleDate(prod.getOnsaleDate());
            product.setProductPrice(prod.getProductPrice());
            product.setProductImage(prod.getProductImage());
            product.setBrandId(prod.getBrandId());
            prodRepo.save(product);
            }
        }

        @PutMapping("/updateprodcolor/{id}")
        public void updateProdColor(@PathVariable long id,@RequestParam("prodcolor") String[] prodcol){
            productcolor[] prodColor = prodcolorRepo.findAllByProductId(id);
            if(prodColor.length == prodcol.length){
                for (int i = 0; i < prodColor.length; i++) {
                    prodColor[i].setColorId(Long.parseLong(prodcol[i]));
                    prodcolorRepo.save(prodColor[i]);
                }
            }else if(prodColor.length > prodcol.length){
                int diff = prodColor.length - prodcol.length;
                for (int j = 0; j < diff; j++) {
                     prodcolorRepo.deleteById(prodColor[prodColor.length - 1].getProductcolorId());
                }
                for (int i = 0; i < prodcol.length; i++) {
                    prodColor[i].setColorId(Long.parseLong(prodcol[i]));
                }
            } else if( prodColor.length < prodcol.length){
                int dif = prodcol.length - prodColor.length ;
                for (int i = 0; i < prodcol.length; i++) {
                    prodColor[i].setColorId(Long.parseLong(prodcol[i]));
                }
                for (int j = 0; j < dif; j++) {
                    List<productcolor> allPC = prodcolorRepo.findAll();
                    productcolor prodcolor = new productcolor(allPC.get(prodcolorRepo.findAll().size()-1).getProductcolorId()+1,prodColor[0].getProductId(),Long.parseLong(prodcol[j]));
                    prodcolorRepo.save(prodcolor);

                }
            }

        }


        @PutMapping("/updateoneprodcolor")
        public void updateOneProdColor(@RequestParam Long productcolorId,
    //                                  @RequestParam Long productId,
                                        @RequestParam Long colorId) {
            productcolor prodC = prodcolorRepo.findById(productcolorId).orElse(null);
            if(prodC != null) {
                prodC.setColorId(colorId);
                prodcolorRepo.save(prodC);
            }
        }
        /* END */
        /* DELETE */

        @DeleteMapping("/delbrand/{id}")
        public void deleteBrandById(@PathVariable long id){
            products[] prod = prodRepo.findAllByProductId(id);
            if(prod.length != 0 ){
                throw new MessageException("The brand code cannot be removed because the product is currently using this brand. ");
            }else{
            brandRepo.deleteById(id);
            }
        }

        @DeleteMapping("/cdelbrand/{id}")
        public void confirmDelBrand(@PathVariable long bid) throws IOException {
            products[] prod1 = prodRepo.findAllByBrandId(bid);
            for (int i = 0; i < prod1.length; i++) {
                Long run = prod1[i].getProductId();
                System.out.println(run);
                productcolor[] prodColor = prodcolorRepo.findAllByProductId(run);
                cart[] cart = cartRepo.findAllByProductId(run);
                productsize[] prodsize = prodsizeRepo.findAllByProductId(run);
                products prod = prodRepo.findById(run).orElse(null);
                if(prod != null){
                    storageService.delete(prod.getProductImage());
                }else{
                    throw new MessageException("does not have id : "+run);
                }

                for (int a = 0; a < cart.length; a++) {
                    cartRepo.deleteById(cart[a].getCartId());
                }
                for (int b = 0; b < prodColor.length; b++) {
                    prodcolorRepo.deleteById(prodColor[b].getProductcolorId());
                }
                for (int c = 0; c < prodsize.length; c++) {
                    prodsizeRepo.deleteById(prodsize[c].getProductsizeId());
                }
                prodRepo.deleteById(run);
            }
//            productcolor[] pc =  prodcolorRepo.findAllByColorId(id);
//            for (int i = 0; i < pc.length; i++) {
//                prodcolorRepo.deleteById(pc[i].getProductcolorId());
//            }
//            prodRepo.deleteAllByBrandId(bid);
            brandRepo.deleteById(bid);
        }

        @DeleteMapping("/delcart/{id}")
        public String deleteCartById(@PathVariable long id){
            cartRepo.deleteById(id);
            return "delete success";
        }

        @DeleteMapping("/delcolor/{id}")
        public void deleteColorById(@PathVariable long id){
            productcolor[] prodColor = prodcolorRepo.findAllByColorId(id);
            if(prodColor.length != 0 ){
                throw new MessageException("The color code cannot be removed because the product is currently using this color. ");
            }else{
                colorRepo.deleteById(id);
            }

        }

        @DeleteMapping("/cdelcolor/{id}")
        public void confirmDelCol(@PathVariable long id){
            productcolor[] pc =  prodcolorRepo.findAllByColorId(id);
            for (int i = 0; i < pc.length; i++) {
                prodcolorRepo.deleteById(pc[i].getProductcolorId());
            }
            colorRepo.deleteById(id);
        }

        @DeleteMapping("/delprodcolor/{id}")
        public void deleteProdColorById(@PathVariable long id){
            prodcolorRepo.deleteById(id);
        }

        @DeleteMapping("/delaccount/{id}")
        public void deleteAcccountById(@PathVariable long id){
            cart[] cart = cartRepo.findAllByAccountId(id);
            for (int i = 0; i < cart.length; i++) {
                cartRepo.deleteById(cart[i].getCartId());
            }
            accRepo.deleteById(id);
        }
        /* ทำเรื่องถ้าจะลบแล้วยังมีสินค้าอยู่ในตะกร้า */
        @DeleteMapping("/delprod/{id}")
        public void deleteProductById(@PathVariable long id) throws IOException {
            productcolor[] prodColor = prodcolorRepo.findAllByProductId(id);
            cart[] cart = cartRepo.findAllByProductId(id);
            productsize[] prodsize = prodsizeRepo.findAllByProductId(id);
            products prod = prodRepo.findById(id).orElse(null);
            if(prod != null){
                storageService.delete(prod.getProductImage());
            }else{
                throw new MessageException("does not have id : "+id);
            }

            for (int i = 0; i < cart.length; i++) {
                cartRepo.deleteById(cart[i].getCartId());
            }
            for (int i = 0; i < prodColor.length; i++) {
                prodcolorRepo.deleteById(prodColor[i].getProductcolorId());
            }
            for (int i = 0; i < prodsize.length; i++) {
                prodsizeRepo.deleteById(prodsize[i].getProductsizeId());
            }
            prodRepo.deleteById(id);
        }
        /* END */

}
