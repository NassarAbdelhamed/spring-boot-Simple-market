import Link from "next/link";
import './admin.css'
export default function Admin(){
    return(
        <div className="adminNav">
            <div className="links">
                <Link href={'/Pages/Admin/AddProduct'}>AddProduct</Link>
                <Link href={'/Pages/Admin/EditProduct'}>EditProduct</Link>
                <Link href={'/Pages/Admin/ChangeValidation'}>ChangeValidation</Link>
            </div>
        </div>
    )
    
}