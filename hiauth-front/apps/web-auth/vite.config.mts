import { defineConfig } from '@vben/vite-config';

export default defineConfig(async () => {
  return {
    application: {},
    vite: {
      server: {
        proxy: {
          '/gateway': {
            changeOrigin: true,
            rewrite: (path) => path.replace(/^\/gateway/, ''),
            target: 'http://127.0.0.1:8080',
            ws: true,
          },
        },
      },
    },
  };
});
