import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";

@Injectable({
    providedIn: 'root'
})
export class ImageService {

    private url = "http://localhost:8080/api/image";

    constructor(private http: HttpClient) { }

    uploadImage(image: any) {
        const formData: any = new FormData();
        formData.append("imageFile", image);
        return this.http.post(this.url + "/upload", formData);
    }
}