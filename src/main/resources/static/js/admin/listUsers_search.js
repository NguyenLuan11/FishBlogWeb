document.addEventListener('DOMContentLoaded', () => {
        const allUsers = window.allUsers || [];
        const searchInput = document.getElementById('searchInput');
        const userCardContainer = document.getElementById('userCardContainer');

        searchInput.addEventListener('input', function () {
            const keyword = this.value.trim().toLowerCase();
            userCardContainer.innerHTML = '';

            const filteredUsers = allUsers.filter(user =>
                user.userName.toLowerCase().includes(keyword)
            );

            if (filteredUsers.length === 0) {
                userCardContainer.innerHTML = `
                    <div class="col">
                        <div class="alert alert-warning text-center w-100" role="alert">
                            Không tìm thấy tài khoản người dùng nào phù hợp!
                        </div>
                    </div>`;
                return;
            }

            filteredUsers.forEach(user => {
                const col = document.createElement('div');
                col.className = 'col-12 col-sm-6 col-lg-3';
                col.innerHTML = `
                    <div class="single_advisor_profile">
                        <div class="advisor_thumb">
                            <a href="/admin/details-user/${user.id}">
                                <img src="${user.avatarUrl || '/img/avatar_user.png'}"
                                     style="height: 250px; width: 250px;" alt="Avatar">
                            </a>
                            <div class="social-info">
                                <a>${formatDate(user.registeredDate)}</a>
                            </div>
                        </div>
                        <div class="single_advisor_details_info">
                            <h6 class="user-name">${user.userName}</h6>
                            <p class="designation">${user.email}</p>
                        </div>
                    </div>
                `;
                userCardContainer.appendChild(col);
            });
        });

        function formatDate(dateString) {
            if (!dateString) return 'Không rõ';
            const date = new Date(dateString);
            if (isNaN(date.getTime())) return 'Không hợp lệ';
            const day = String(date.getDate()).padStart(2, '0');
            const month = String(date.getMonth() + 1).padStart(2, '0');
            const year = date.getFullYear();
            return `${day}-${month}-${year}`;
        }
    });