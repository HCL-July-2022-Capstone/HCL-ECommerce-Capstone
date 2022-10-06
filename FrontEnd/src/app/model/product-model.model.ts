export class ProductModel {
  productId: number;
  productName: string;
  productDescription: string;
  productPrice: number;
  quantityOnHand: number;
  categoryName: string;
  image: string;

  constructor(product: ProductModel) {
    this.productId = product.productId;
    this.productName = product.productName;
    this.image = product.image;
    this.productPrice = product.productPrice;
    this.productDescription = product.productDescription;
    this.quantityOnHand = product.quantityOnHand;
    this.categoryName = product.categoryName;
  }
}
