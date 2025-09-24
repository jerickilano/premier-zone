# Quick Railway Deployment Steps

## 🚀 Deploy Your Backend to Railway

### 1. Go to Railway
Visit: https://railway.app

### 2. Sign Up/Login
- Click "Login" 
- Choose "Continue with GitHub"
- Authorize Railway to access your repositories

### 3. Create New Project
- Click "New Project"
- Select "Deploy from GitHub repo"
- Find and select your `premier-zone` repository
- Click "Deploy Now"

### 4. Add Database
- In your project dashboard, click "New"
- Select "Database" → "PostgreSQL"
- Railway will automatically provision a PostgreSQL database

### 5. Set Environment Variables
Go to your service → Variables tab and add:
```
SPRING_PROFILES_ACTIVE=prod
CORS_ALLOWED_ORIGINS=https://frontend-tu0c0mbe7-jerickilanos-projects.vercel.app
```

### 6. Get Your Backend URL
- Railway will provide a URL like: `https://your-app-name.railway.app`
- Copy this URL

### 7. Update Frontend
- Go to Vercel dashboard: https://vercel.com/dashboard
- Select your `frontend` project
- Go to Settings → Environment Variables
- Add: `REACT_APP_API_URL` = your Railway URL
- Redeploy your frontend

### 8. Test Your App
- Visit your Vercel frontend URL
- Test the CRUD operations
- Your app should now be fully functional!

## 📝 Notes
- Railway automatically detects Java/Spring Boot projects
- Database connection is handled automatically
- No need for complex configuration files
- Railway provides free tier with generous limits

## 🔧 If You Need Help
- Check Railway logs in the dashboard
- Ensure environment variables are set correctly
- Verify your GitHub repository is connected
