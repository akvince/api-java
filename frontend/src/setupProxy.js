const { createProxyMiddleware } = require('http-proxy-middleware');

module.exports = function (app) {
    app.use(
        '/api',
        createProxyMiddleware({
            target: 'http://spring-app.api.orb.local:8082',
            changeOrigin: true,
        })
    );
};
