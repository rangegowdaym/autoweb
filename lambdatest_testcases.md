# Automation Test Cases for https://ecommerce-playground.lambdatest.io/

## 1. Home Page

- **TC001**: Verify the home page loads successfully and all major sections (header, search bar, navigation menu, featured products, footer) are visible.
- **TC002**: Validate that the website logo redirects to the home page from any other page.
- **TC003**: Check that promotional banners (if any) are displayed and clickable.

## 2. User Registration & Authentication

- **TC004**: Register a new user with valid details and verify successful account creation.
- **TC005**: Attempt to register with an existing email and verify appropriate error message.
- **TC006**: Login with valid credentials and verify redirection to user dashboard/account page.
- **TC007**: Login with invalid credentials and verify error message.
- **TC008**: Test "Forgot Password" functionality with registered and unregistered emails.

## 3. Product Search & Filtering

- **TC009**: Search for a product using the search bar and verify relevant results.
- **TC010**: Apply category filters (e.g., price, brand, rating) and validate the filtered results.
- **TC011**: Verify sorting options (e.g., price low to high, high to low, popularity) work as expected.

## 4. Product Details Page

- **TC012**: Click on a product from any listing and verify product details page loads.
- **TC013**: Verify product images, price, description, specifications, and reviews are displayed.
- **TC014**: Test image zoom and gallery functionalities.
- **TC015**: Add product to wishlist and verify it appears in the wishlist.

## 5. Cart Functionality

- **TC016**: Add a product to cart from listing and product details page.
- **TC017**: Update product quantity in cart and verify total price calculation.
- **TC018**: Remove product from cart and confirm cart updates accordingly.
- **TC019**: Attempt to add out-of-stock product to cart and validate error/notification.

## 6. Checkout Process

- **TC020**: Proceed to checkout as a guest user; verify guest checkout is possible or prompt for login/registration.
- **TC021**: Complete checkout with valid billing, shipping, and payment details.
- **TC022**: Attempt checkout with invalid/empty required fields and verify validations.
- **TC023**: Apply valid and invalid coupon codes and verify discounts/error messages.
- **TC024**: Verify order confirmation page and email notification (if applicable).

## 7. Order History & Account Management

- **TC025**: Log in and verify order history displays past orders.
- **TC026**: View details of a past order.
- **TC027**: Edit account information (name, email, password) and verify updates.
- **TC028**: Change password and confirm login with new password.

## 8. Wishlist & Compare

- **TC029**: Add multiple products to wishlist and verify all are listed.
- **TC030**: Remove product from wishlist.
- **TC031**: Add products to compare and verify comparison table displays correct details.

## 9. UI/UX & Responsiveness

- **TC032**: Validate website layout and functionality on different screen sizes (desktop, tablet, mobile).
- **TC033**: Test navigation menu and hamburger menu on mobile.
- **TC034**: Verify that all links and buttons are accessible via keyboard (accessibility check).

## 10. Miscellaneous

- **TC035**: Verify contact us form submission with valid and invalid details.
- **TC036**: Validate newsletter subscription with valid/invalid email.
- **TC037**: Check social media links (if present) open in new tabs.
- **TC038**: Verify footer links (privacy policy, terms of service, about us, etc.) navigate correctly.

---

> **Note:**  
- Each test case should be further detailed with preconditions, steps, expected results, and postconditions for implementation.
- Include negative and edge cases for form validations, error handling, and internationalization if the site supports multiple languages.
- Integrate assertions for UI elements, API responses (if automating at API level), and database validations for end-to-end coverage.
