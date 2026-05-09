/** @type {import('tailwindcss').Config} */
module.exports = {
  darkMode: 'class',
  content: [
    './src/**/*.{html,js}'
  ],
  theme: {
    extend: {
      fontFamily: {
        'inter': ["'Inter'", 'sans-serif']
      },
      container:{
        center:true,
        padding: '1px'
      },
      colors:{
        primary: '#10b981',
        secondary: '#64748b', 
        dark: '#0f172a'
      },
    },
  },
  plugins: [
    
  ],
}

