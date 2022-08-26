import react from "@vitejs/plugin-react";
import { defineConfig, splitVendorChunkPlugin } from "vite";
import svgr from 'vite-plugin-svgr';

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [
    react(),
    splitVendorChunkPlugin(),
    svgr({
      exportAsDefault: true,
    }),
  ],
  build: {
    outDir: "./public/build",
  },
  server: {
    // this is used to handle CORS in dev
    proxy: {
      "/backend": {
        target: "http://localhost:8080",
        changeOrigin: true,
        secure: false,
        rewrite: (path) => path.replace(/^\/backend/, ""),
      },
    },
  },
});
