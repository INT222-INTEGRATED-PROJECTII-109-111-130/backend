package IntegratedProject.int222.controllers;

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

    final StorageService storageService;

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
    public List<Accounts> showAcc() {
        return  accRepo.findAll();
    }

    @GetMapping("/showallbrand")
    public List<Brands> showBrand() {
        return  brandRepo.findAll();
    }

    @GetMapping("/showallcart")
    public List<Cart> showCart() {
        return  cartRepo.findAll();
    }

    @GetMapping("/showallcolor")
    public List<Colors> showColor() {
        return  colorRepo.findAll();
    }

    @GetMapping("/showallprodcolor")
    public List<Productcolor> showProdColor() {
        System.out.println(prodcolorRepo.findAll().size());
        List<Productcolor> allPC = prodcolorRepo.findAll();
        System.out.println(allPC.get(prodcolorRepo.findAll().size()-1).getProductcolorId());
        return  prodcolorRepo.findAll();
    }

    @GetMapping("/showallproduct")
    public List<Products> showProd() {
        return  prodRepo.findAll();
    }
    /* END */
    /* POST */
    @PostMapping("/addbrand")
    public void addBrand(@RequestBody Brands bname) {
        brandRepo.save(bname);
    }

    @PostMapping("/addcolor")
    public void addColor(@RequestBody Colors color) {
        colorRepo.save(color);
    }

    /* ยังไม่เสร็จ เช็คเงื่อนไขว่มีซ้ำไหม */
    @PostMapping("/addaccount")
    public void addAccount(@RequestBody Accounts acc) {
        accRepo.save(acc);
    }
    /* END */

    @PostMapping("/addcart")
    public void addCart(@RequestBody Cart cart) {
        cartRepo.save(cart);
    }

    @PostMapping("/addprodcolor")
    public void addProdColor(@RequestBody Productcolor prodColor) {
        prodcolorRepo.save(prodColor);
    }

    @PostMapping("/addprod")
    public void addProd(@RequestParam("prod") String produc
                        ,@RequestParam("prodcolor") String[] prodcolor
                        ,@RequestParam("file") MultipartFile file){
        Products product ;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            product = objectMapper.readValue(produc, Products.class);
            prodRepo.save(product);
            for (int i = 0; i < prodcolor.length; i = ++i) {
//                System.out.println(i);
//                System.out.println(Long.parseLong(prodcolor[i]));
//                System.out.println(Long.parseLong(prodcolor[i+1]));
                Productcolor productcolor = new Productcolor(Long.parseLong(prodcolor[i]),product.getProductId(),Long.parseLong(prodcolor[++i]));
                prodcolorRepo.save(productcolor);
            }
            storageService.store(file);
        }catch (IOException err){
            System.out.printf("Error",err.toString());
        }
    }
    /* END*/

    /* ยังไม่ได้test */
        /* PUT */
        @PutMapping("/updateaccount")
        public void updateAccount(@RequestBody Accounts acc){
            Accounts account = accRepo.findById(acc.getAccountId()).orElse(null);
            if(account != null){
                account.setFirstName(acc.getFirstName());
                account.setLastName(acc.getLastName());
            }
        }

        /*method เปลี่ยน pass */

        @PutMapping("/updatebrand")
        public void updateBrand(@RequestBody Brands b){
            Brands brand = brandRepo.findById(b.getBrandId()).orElse(null);
            if(brand != null){
                brand.setBrandName(b.getBrandName());
                brandRepo.save(brand);
            }
        }

        @PutMapping("/updateprod")
        public void updateProd(@RequestBody Products prod) {
            Products product = prodRepo.findById(prod.getProductId()).orElse(null);
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
            Productcolor[] prodColor = prodcolorRepo.findAllByProductId(id);
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
                    List<Productcolor> allPC = prodcolorRepo.findAll();
                    Productcolor prodcolor = new Productcolor(allPC.get(prodcolorRepo.findAll().size()-1).getProductcolorId()+1,prodColor[0].getProductId(),Long.parseLong(prodcol[j]));
                    prodcolorRepo.save(prodcolor);

                }
            }
        }


        @PutMapping("/updateoneprodcolor")
        public void updateOneProdColor(@RequestParam Long productcolorId,
    //                                  @RequestParam Long productId,
                                        @RequestParam Long colorId) {
            Productcolor prodC = prodcolorRepo.findById(productcolorId).orElse(null);
            if(prodC != null) {
                prodC.setColorId(colorId);
                prodcolorRepo.save(prodC);
            }
        }
        /* END */
        /* DELETE */

        @DeleteMapping("/delbrand/{id}")
        public void deleteBrandById(@PathVariable long id){
            brandRepo.deleteById(id);
        }

        @DeleteMapping("/delcart/{id}")
        public void deleteCartById(@PathVariable long id){
            cartRepo.deleteById(id);
        }

        @DeleteMapping("/delcolor/{id}")
        public void deleteColorById(@PathVariable long id){
            colorRepo.deleteById(id);
        }

        @DeleteMapping("/delprodcolor/{id}")
        public void deleteProdColorById(@PathVariable long id){
            prodcolorRepo.deleteById(id);
        }

        @DeleteMapping("/delaccount/{id}")
        public void deleteAcccountById(@PathVariable long id){
            Cart[] cart = cartRepo.findAllByAccountId(id);
            for (int i = 0; i < cart.length; i++) {
                cartRepo.deleteById(cart[i].getCartId());
            }
            accRepo.deleteById(id);
        }
        /* ทำเรื่องถ้าจะลบแล้วยังมีสินค้าอยู่ในตะกร้า */
        @DeleteMapping("/delprod/{id}")
        public void deleteProductById(@PathVariable long id){
            Productcolor[] prodColor = prodcolorRepo.findAllByProductId(id);
            Cart[] cart = cartRepo.findAllByProductId(id);
            for (int i = 0; i < cart.length; i++) {
                cartRepo.deleteById(cart[i].getCartId());
            }
            for (int i = 0; i < prodColor.length; i++) {
                prodcolorRepo.deleteById(prodColor[i].getProductcolorId());
            }
            prodRepo.deleteById(id);
        }
        /* END */

}
