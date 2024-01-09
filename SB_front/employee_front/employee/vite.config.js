import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [
    vue(),
  ],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    }
  },
  server: {
    port: 3001,
    hmr:true,  // 热部署
    proxy: {
      "/api": {
        target:"http://localhost:9091/",
        changeOrigin:true,  // 请求跨域
        // 将请求中api换为冒号后面的东西
        pathRewrite: {
          "^api": "/api"
        }
      }
    }
  }
})
