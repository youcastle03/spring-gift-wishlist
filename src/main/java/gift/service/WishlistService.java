package gift.service;

import gift.domain.Wishlist;
import gift.repository.ProductRepository;
import gift.repository.WishlistRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class WishlistService {
    private final WishlistRepository wishlistRepository;
    private final ProductService productService;

    public WishlistService(WishlistRepository wishlistRepository, ProductService productService) {
        this.wishlistRepository = wishlistRepository;
        this.productService = productService;
    }

    public List<Wishlist> getWishlist(Long userId) {
        return wishlistRepository.findWishList(userId);
    }

    public boolean addWishlist(Wishlist wishlist){
        if(productService.getProduct(wishlist.getProductId()) == null){
            return false;
        }
        Wishlist wishlistItem = new Wishlist(wishlist.getUserId(),wishlist.getProductId(),wishlist.getQuantity());
        return wishlistRepository.addWishItem(wishlist) == 1;
    }

    public boolean updateWishlistItem(Wishlist wishlist){
        if(productService.getProduct(wishlist.getProductId()) == null){
            return false;
        }
        Wishlist wishlistItem = new Wishlist(wishlist.getUserId(),wishlist.getProductId(),wishlist.getQuantity());
        return wishlistRepository.updateWishlistItem(wishlistItem) == 1;
    }

    public boolean deleteWishlist(Wishlist wishlist){
        return wishlistRepository.deleteWishItem(wishlist) == 1;
    }

}