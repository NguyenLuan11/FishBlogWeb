const pageSize = 9;
let filteredData = [];
let currentPage = 1;

document.addEventListener('DOMContentLoaded', function () {
    const allFishBlogs = window.allFishBlogs || [];

    const searchInput = document.getElementById('searchInput');
    const pagedList = document.getElementById('pagedList');
    const searchList = document.getElementById('searchList');
    const searchResults = document.getElementById('searchResults');
    const searchNotFound = document.getElementById('searchNotFound');
    const searchPagination = document.getElementById('searchPagination');
    const searchPaginationContainer = document.getElementById('searchPaginationContainer');

    searchInput.addEventListener('input', function () {
        const keyword = this.value.trim().toLowerCase();

        if (keyword === "") {
            pagedList.style.display = 'block';
            searchList.style.display = 'none';
            return;
        }

        filteredData = allFishBlogs.filter(kf =>
            kf.fishName.toLowerCase().includes(keyword)
        );

        currentPage = 1;
        renderSearchResults(filteredData, currentPage);

        pagedList.style.display = 'none';
        searchList.style.display = 'block';
    });

    window.goToPage = function (page) {
        currentPage = page;
        renderSearchResults(filteredData, currentPage);
    }

    function formatDate(dateString) {
        if (!dateString) return 'Không rõ';
        const date = new Date(dateString);
        if (isNaN(date.getTime())) return 'Không hợp lệ';
        const day = String(date.getDate()).padStart(2, '0');
        const month = String(date.getMonth() + 1).padStart(2, '0');
        const year = date.getFullYear();
        return `${day}/${month}/${year}`;
    }

    function renderSearchResults(data, page) {
        const start = (page - 1) * pageSize;
        const end = start + pageSize;
        const itemsToShow = data.slice(start, end);

        searchResults.innerHTML = '';

        if (data.length === 0) {
            searchNotFound.innerHTML = `
                <div class="col" style="min-height: 70px;">
                    <div class="alert alert-warning text-center w-100" role="alert">
                        Không tìm thấy blog cá cảnh nào phù hợp!
                    </div>
                </div>
            `;
            searchPaginationContainer.style.display = 'none';
            return;
        }

        itemsToShow.forEach(item => {
            const col = document.createElement('div');
            col.className = 'col';

            col.innerHTML = `
                <div class="card shadow-sm">
                    <img src="${item.thumbnailUrl}" class="card-img-top img-fluid" width="100%" height="225" alt="Thumbnail Blog">
                    <div class="card-body">
                        <p class="card-text fw-bold fs-4">${item.fishName}</p>
                        <div class="d-flex justify-content-between align-items-center">
                            <div class="btn-group p-2">
                                <a href="${item.thumbnailUrl}" title="${item.fishName}" data-gallery="blogs-gallery-app" class="glightbox preview-link me-2">
                                    <i class="fa-solid fa-magnifying-glass"></i>
                                </a>
                                <a href="/admin/details-fishBlog/${item.id}" title="More Details" class="details-link">
                                    <i class="fa-solid fa-link"></i>
                                </a>
                            </div>
                            <small class="text-muted">Ngày chỉnh sửa • ${formatDate(item.modifiedDate)}</small>
                        </div>
                    </div>
                </div>
            `;

            searchResults.appendChild(col);
        });

        renderPagination(data.length, page);
    }

    function renderPagination(totalItems, page) {
        const totalPages = Math.ceil(totalItems / pageSize);

        if (totalPages <= 1) {
            searchPaginationContainer.style.display = 'none';
            return;
        }

        searchPaginationContainer.style.display = 'flex';
        searchPagination.innerHTML = '';

        for (let i = 1; i <= totalPages; i++) {
            const li = document.createElement('li');
            li.className = `page-item ${i === page ? 'active' : ''}`;
            li.innerHTML = `<button class="page-link" onclick="goToPage(${i})">${i}</button>`;
            searchPagination.appendChild(li);
        }
    }

    function truncate(text, maxLength) {
        return text && text.length > maxLength ? text.substring(0, maxLength) + '...' : text;
    }
});
