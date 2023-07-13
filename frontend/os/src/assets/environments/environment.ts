// This file can be replaced during build by using the `fileReplacements` array.
// `ng build --prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
  production: false,
  url_api: 'http://localhost:8080/olimac/usuarios',
  url_api_delete: 'http://localhost:8080/olimac/usuarios/delete',
  url_roles: 'http://localhost:8080/olimac/roles',
  productoURL: 'http://localhost:8080/producto/',
  authURL: 'http://localhost:8080/auth/',

};

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/dist/zone-error';  // Included with Angular CLI.
