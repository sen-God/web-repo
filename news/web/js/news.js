function loadNewsByCategory(category) {
    const url = `/api/news${category ? `?category=${category}` : ''}`;
    fetch(url)
        .then(response => response.json())
        .then(data => {
            const newsContainer = document.getElementById('newsContainer');
            newsContainer.innerHTML = '';
            data.forEach(news => {
                const newsElement = createNewsElement(news);
                newsContainer.appendChild(newsElement);
            });
        })
        .catch(error => console.error('Error:', error));
}

function searchNews() {
    const searchInput = document.getElementById('searchInput');
    const searchTerm = searchInput.value.trim();
    if (searchTerm) {
        fetch(`/api/news?search=${encodeURIComponent(searchTerm)}`)
            .then(response => response.json())
            .then(data => {
                const newsContainer = document.getElementById('newsContainer');
                newsContainer.innerHTML = '';
                data.forEach(news => {
                    const newsElement = createNewsElement(news);
                    newsContainer.appendChild(newsElement);
                });
            })
            .catch(error => console.error('Error:', error));
    }
}

function createNewsElement(news) {
    const div = document.createElement('div');
    div.className = 'news-item';
    div.innerHTML = `
        <h2>${news.title}</h2>
        <p>${news.content}</p>
        <div class="news-category">${news.category}</div>
    `;
    return div;
}

// 初始加载
document.addEventListener('DOMContentLoaded', () => {
    loadNewsByCategory('');
});