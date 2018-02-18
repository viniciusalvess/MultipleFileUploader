/*

    Before using make sure you have:
    npm install --save-dev gulp gulp-minify-css gulp-concat gulp-uglify gulp-autoprefixer gulp-sass

    Make sure to change the directory names in the default watch function to the CSS/SCSS/SASS directories you are using so it reloads
 */
var gulp = require('gulp');
var minifyCSS = require('gulp-minify-css');
var concat = require('gulp-concat');
var uglify = require('gulp-uglify');
var prefix = require('gulp-autoprefixer');
var sass = require('gulp-sass');
var rename = require('gulp-rename');

// Minifies JS
gulp.task('js', function(){
    return gulp.src('src/main/resources/assets/js/*.js')
        .pipe(uglify())
        .pipe(rename({ suffix: '.min' }))
        .pipe(gulp.dest('src/main/resources/static/js'))
});

/*==========  Minify and concat different styles files  ==========*/

// SCSS Version
// gulp.task('styles', function(){
//     return gulp.src('src/main/resources/assets/scss/**/*.scss')
//         .pipe(sass())
//         .pipe(prefix('last 2 versions'))
//         .pipe(concat('main.css'))
//         .pipe(minifyCSS())
//         .pipe(gulp.dest('src/main/resources/static/css'))
// });

// CSS Version
/*
gulp.task('styles', function(){
    return gulp.src('src/css/*.css')
    .pipe(concat('site.css'))
    .pipe(minifyCSS())
    .pipe(prefix('last 2 versions'))
    .pipe(gulp.dest('public/css'))
});
*/

gulp.task('watch', function () {
    gulp.run('js');
    gulp.watch('src/main/resources/assets/js/**/*.js', ['js']);
});


gulp.task('default', ['watch']);

// gulp.task('watch', function () {
//     var server = ['jasmine', 'embed'];
//     var client = ['scripts', 'styles', 'copy', 'lint'];
//     gulp.watch('app/*.js', server);
//     gulp.watch('spec/nodejs/*.js', server);
//     gulp.watch('app/backend/*.js', server);
//     gulp.watch('src/admin/*.js', client);
//     gulp.watch('src/admin/*.css', client);
//     gulp.watch('src/geojson-index.json', ['copygeojson']);
// });


// var gulp = require('gulp');
// // var pug = require('gulp-pug');
// var less = require('gulp-less');
// var minifyCSS = require('gulp-csso');
//
// // gulp.task('html', function(){
// //   return gulp.src('client/templates/*.pug')
// //     .pipe(pug())
// //     .pipe(gulp.dest('build/html'))
// // });
//
// gulp.task('css', function(){
//   return gulp.src('src/main/resources/static/bower_components/patternfly/src/less/*.less')
//     .pipe(less())
//     .pipe(minifyCSS())
//     .pipe(gulp.dest('src/main/resources/static/build/css'))
// });
//
// gulp.task('default', ['css']);
          