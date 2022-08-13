const { createProxyMiddleware } = require('http-proxy-middleware');

module.exports = function(app) {
    app.use(
        '/api',    // 프론트엔드에서 '/api'로 요청을 보내면 8080 포트(target)로 요청이 도착함
        createProxyMiddleware({
            target: 'http://localhost:8080',   // 서버 URL or localhost:설정한포트번호
            changeOrigin: true,
        })
    );
};