# Railway Deployment Guide

## Step 1: Create Railway Account
1. Go to [railway.app](https://railway.app)
2. Sign up with GitHub
3. Connect your GitHub account

## Step 2: Deploy Backend
1. Click "New Project" in Railway dashboard
2. Select "Deploy from GitHub repo"
3. Choose your `premier-zone` repository
4. Railway will automatically detect it's a Java/Spring Boot project

## Step 3: Add PostgreSQL Database
1. In your Railway project, click "New"
2. Select "Database" → "PostgreSQL"
3. Railway will automatically create a PostgreSQL database
4. The `DATABASE_URL` environment variable will be automatically set

## Step 4: Configure Environment Variables
In Railway dashboard, go to your service → Variables tab and add:
- `SPRING_PROFILES_ACTIVE=prod`
- `CORS_ALLOWED_ORIGINS=https://frontend-tu0c0mbe7-jerickilanos-projects.vercel.app`

## Step 5: Deploy
1. Railway will automatically build and deploy your application
2. Your backend will be available at the Railway-provided URL
3. Copy this URL for the next step

## Step 6: Update Frontend
1. Go to your Vercel dashboard
2. Go to your project → Settings → Environment Variables
3. Add: `REACT_APP_API_URL` = your Railway backend URL
4. Redeploy your frontend

## Step 7: Load Data
Once deployed, you'll need to load your CSV data:
1. Use the Railway CLI or dashboard to access your database
2. Import your `prem_stats.csv` data
3. Or create an endpoint to load data from the CSV file

## Railway CLI (Optional)
```bash
# Install Railway CLI
npm install -g @railway/cli

# Login
railway login

# Link to your project
railway link

# Deploy
railway up
```

## Troubleshooting
- Check Railway logs if deployment fails
- Ensure all environment variables are set correctly
- Verify database connection in Railway dashboard
