module.exports=function(grunt){
	
	grunt.initConfig({
		concat:{
			options:{
				separator: '\n\n',
				stripBanners:true
			},
			dist: {
			      src: ['angularJs/amoeba.js','angularJs/modules/*/*.js','angularJs/modules/login/**/*.js','angularJs/modules/main/**/*.js','angularJs/modules/profile/**/*.js','angularJs/modules/form/**/*.js'],
			      dest: 'dist/amoeba.js',
			 }
		},
		eslint: {
			configFile: ".eslintrc",
		    format: require('eslint-html-reporter'),
		    outputFile: "reports/eslint-report.html",
	        target: ['angularJs/amoeba.js','angularJs/modules/*/*.js','angularJs/modules/login/**/*.js','angularJs/modules/main/**/*.js','angularJs/modules/profile/**/*.js','angularJs/modules/form/**/*.js']
	    }
	})
	
	require('load-grunt-tasks')(grunt);
	
	grunt.registerTask('ui-deploy',['concat','eslint']);
	
};