const pageSize = 12;
let filteredData = [];
let currentPage = 1;

document.addEventListener('DOMContentLoaded', function () {
    const allKindFish = window.allKindFish || [];

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

        filteredData = allKindFish.filter(kf =>
            kf.kindFishName.toLowerCase().includes(keyword)
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
                        Không tìm thấy loại cá nào phù hợp!
                    </div>
                </div>
            `;
            searchPaginationContainer.style.display = 'none';
            return;
        }

        itemsToShow.forEach(item => {
            const card = document.createElement('div');
            card.className = 'col';
            card.innerHTML = `
                <div class="card h-100 shadow-sm">
                   <div class="post-meta" style="padding: 5px; padding-left: 10px;">
                       <span class="date">Ngày chỉnh sửa</span> <span class="mx-1">•</span>
                       <span>${formatDate(item.modifiedDate)}</span>
                   </div>
                   <a href="/admin/details-kindFish/${item.id}">
                        <img src="${item.imageUrl}" class="card-img-top" style="height:220px; object-fit:cover;">
                   </a>
                   <div class="card-body">
                       <h5 class="card-title">${item.kindFishName}</h5>
                       <p class="card-text" style="text-align:justify">${truncate(item.description, 200)}</p>
                       <div class="d-flex justify-content-around">
                           <a class="btn btn-outline-info" href="/admin/update-kindFish/${item.id}">Cập nhật</a>
                           <a class="btn btn-outline-danger" href="/admin/delete-kindFish/${item.id}">Xóa</a>
                       </div>
                    </div>
                </div>
            `;
            searchResults.appendChild(card);
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
