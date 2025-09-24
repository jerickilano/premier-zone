# Premier Zone Deployment Guide

## Vercel Deployment (Frontend)

### Step 1: Prepare Frontend for Production
1. Update the API URL in your frontend to point to your production backend
2. Build the frontend: `cd frontend && npm run build`

### Step 2: Deploy to Vercel
1. Install Vercel CLI: `npm i -g vercel`
2. Login to Vercel: `vercel login`
3. Deploy: `vercel --prod`

### Step 3: Configure Environment Variables
In Vercel dashboard, add:
- `REACT_APP_API_URL`: Your backend URL (e.g., Heroku app URL)

## Backend Deployment Options

### Option 1: Heroku (Recommended)
1. Create a Heroku app
2. Add PostgreSQL addon
3. Deploy using Git or Heroku CLI

### Option 2: Railway
1. Connect your GitHub repository
2. Add PostgreSQL service
3. Deploy automatically

### Option 3: Render
1. Connect your repository
2. Add PostgreSQL database
3. Deploy as web service

## Quick Start Commands

```bash
# Frontend build
cd frontend
npm install
npm run build

# Deploy to Vercel
vercel --prod

# Backend deployment (Heroku example)
git add .
git commit -m "Deploy to production"
git push heroku main
```

## Environment Variables Needed

### Frontend (.env)
```
REACT_APP_API_URL=https://your-backend-url.herokuapp.com
```

### Backend (application-prod.properties)
```
spring.datasource.url=${DATABASE_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
spring.jpa.hibernate.ddl-auto=validate
```
