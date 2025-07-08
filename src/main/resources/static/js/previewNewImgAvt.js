function previewNewImg() {
    const fileInput = document.getElementById("imageAvt");

    // Optional: Preview new Image
    if (fileInput.files && fileInput.files[0]) {
        const reader = new FileReader();
        reader.onload = function (e) {
           const imgs = document.querySelectorAll('.about-avatar img');
           imgs.forEach(img => img.src = e.target.result);
        };
        reader.readAsDataURL(fileInput.files[0]);
        } else {
           const imgs = document.querySelectorAll('.about-avatar img');
           imgs.forEach(img => img.src = originalAvatarUrl);
        }
}