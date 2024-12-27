function loadAds() {
    fetch('/api/ads')
        .then(response => response.json())
        .then(data => {
            const adContainer = document.getElementById('adContainer');
            data.forEach(ad => {
                const adElement = createAdElement(ad);
                adContainer.appendChild(adElement);
            });
        })
        .catch(error => console.error('Error:', error));
}

function createAdElement(ad) {
    const div = document.createElement('div');
    div.className = `ad-item ${ad.type}`;

    if (ad.type === 'banner') {
        div.innerHTML = `
            <img src="${ad.imageUrl}" alt="${ad.title}">
            <div class="ad-content">${ad.content}</div>
        `;
    } else if (ad.type === 'popup') {
        div.innerHTML = `
            <div class="popup-ad">
                <div class="close-btn" onclick="this.parentElement.remove()">×</div>
                <h3>${ad.title}</h3>
                <p>${ad.content}</p>
            </div>
        `;
    }

    return div;
}

// 初始加载广告
document.addEventListener('DOMContentLoaded', () => {
    loadAds();
});