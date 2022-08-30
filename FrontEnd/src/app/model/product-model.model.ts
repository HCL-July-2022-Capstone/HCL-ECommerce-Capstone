export interface ProductModel {
  productId: number;
  productName: string;
  productDescription: string;
  productPrice: number;
  quantityOnHand: number;
  categoryName: string;
  // image!: string;

  // constructor(productId: number, productName: string, productDescription: string,
  //             productPrice: number, quantityOnHand: number, categoryName: string,
  //             categoryId: number, image: string) {
  //   this.productId = productId;
  //   this.productName = productName;
  //   this.productDescription = productDescription;
  //   this.productPrice = productPrice;
  //   this.quantityOnHand = quantityOnHand;
  //   this.categoryName = categoryName;
  //   this.categoryId = categoryId;
  //   this.image = image;
  // }

  // getName(): string {
  //   return `${this.productName} ${this.categoryName}`;
  // }
  //
  // getYearlySalary(): number {
  //   return 12 * this.categoryId;
  // }
}
