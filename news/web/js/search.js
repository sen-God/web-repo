function searchNews() {
    const searchInput = document.getElementById('searchInput');
    const searchTerm = searchInput.value.trim();
    if (searchTerm) {
        const contextPath = window.location.pathname.substring(0, window.location.pathname.indexOf('/', 1));
        const url = `${contextPath}/api/news?search=${encodeURIComponent(searchTerm)}`;

        fetch(url)
            .then(response => {
                if (!response.ok) {
                    throw new Error(`HTTP error! status: ${response.status}`);
                }
                return response.json();
            })
            .then(data => {
                const newsContainer = document.getElementById('newsContainer');
                newsContainer.innerHTML = '';
                if (data && data.length > 0) {
                    data.forEach(news => {
                        const newsElement = createNewsElement(news);
                        newsContainer.appendChild(newsElement);
                    });
                } else {
                    newsContainer.innerHTML = '<div class="no-news">未找到相关新闻</div>';
                }
            })
            .catch(error => {
                console.error('Error:', error);
                const newsContainer = document.getElementById('newsContainer');
                newsContainer.innerHTML = '<div class="error">搜索失败，请稍后重试</div>';
            });
    }
}

// 添加搜索框回车事件监听
document.addEventListener('DOMContentLoaded', () => {
    const searchInput = document.getElementById('searchInput');
    if (searchInput) {
        searchInput.addEventListener('keypress', (e) => {
            if (e.key === 'Enter') {
                searchNews();
            }
        });
    }
});