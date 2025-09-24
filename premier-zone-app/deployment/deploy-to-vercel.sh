#!/bin/bash

echo "Deploying Premier Zone to Vercel..."

echo ""
echo "Step 1: Building frontend..."
cd ../frontend
npm install
npm run build

echo ""
echo "Step 2: Deploying to Vercel..."
cd ../deployment
vercel --prod

echo ""
echo "Deployment complete!"
echo "Your app should be available at the URL provided by Vercel."
